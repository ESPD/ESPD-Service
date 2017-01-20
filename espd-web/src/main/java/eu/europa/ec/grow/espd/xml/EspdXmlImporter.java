/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

package eu.europa.ec.grow.espd.xml;

import com.google.common.base.Optional;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.tenderned.exception.TedNoticeException;
import eu.europa.ec.grow.espd.xml.request.importing.UblRequestImporter;
import eu.europa.ec.grow.espd.xml.response.importing.UblRequestResponseMerger;
import eu.europa.ec.grow.espd.xml.response.importing.UblResponseImporter;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class used to import XML files containing ESPD Requests or Responses.
 * <p>
 * Created by ratoico on 1/23/17.
 */
@Slf4j
@Component
public class EspdXmlImporter {

	private final Jaxb2Marshaller jaxb2Marshaller;
	private final UblRequestImporter requestToEspdDocumentTransformer;
	private final UblResponseImporter responseToEspdDocumentTransformer;
	private final UblRequestResponseMerger requestResponseMerger;

	@Autowired
	EspdXmlImporter(Jaxb2Marshaller jaxb2Marshaller, UblRequestImporter requestToEspdDocumentTransformer,
			UblResponseImporter responseToEspdDocumentTransformer, UblRequestResponseMerger requestResponseMerger) {
		this.jaxb2Marshaller = jaxb2Marshaller;
		this.requestToEspdDocumentTransformer = requestToEspdDocumentTransformer;
		this.responseToEspdDocumentTransformer = responseToEspdDocumentTransformer;
		this.requestResponseMerger = requestResponseMerger;
	}

	/**
	 * Convert a {@link ESPDRequestType} coming from an input stream into a {@link EspdDocument} object needed by
	 * the web application user interface.
	 *
	 * @param espdRequestStream An input stream containing the ESPD Request
	 *
	 * @return An {@link EspdDocument} object coming out from the stream if it contained a valid ESPD Request
	 * wrapped in an {@link Optional} or an empty {@link Optional} if the import was unsuccessful.
	 */
	@SuppressWarnings("unchecked")
	public Optional<EspdDocument> importEspdRequest(InputStream espdRequestStream) {
		try {
			JAXBElement<ESPDRequestType> element = (JAXBElement<ESPDRequestType>) jaxb2Marshaller
					.unmarshal(new StreamSource(espdRequestStream));
			ESPDRequestType requestType = element.getValue();
			return Optional.of(requestToEspdDocumentTransformer.importRequest(requestType));
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
			return Optional.absent();
		}
	}

	/**
	 * Convert a {@link ESPDResponseType} coming from an input stream into a {@link EspdDocument} object needed by
	 * the web application user interface.
	 *
	 * @param espdResponseStream An input stream containing the ESPD Response
	 *
	 * @return An {@link EspdDocument} object coming out from the stream if it contained a valid ESPD Response
	 * wrapped in an {@link Optional} or an empty {@link Optional} if the import was unsuccessful.
	 */
	@SuppressWarnings("unchecked")
	public Optional<EspdDocument> importEspdResponse(InputStream espdResponseStream) {
		try {
			JAXBElement<ESPDResponseType> element = (JAXBElement<ESPDResponseType>) jaxb2Marshaller
					.unmarshal(new StreamSource(espdResponseStream));
			ESPDResponseType responseType = element.getValue();
			return Optional.of(responseToEspdDocumentTransformer.importResponse(responseType));
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
			return Optional.absent();
		}
	}

	/**
	 * Read from an input stream that should contain a {@link ESPDRequestType} or {@link ESPDResponseType}
	 * and convert it into a {@link EspdDocument}. It is not known beforehand if the content
	 * belongs to a ESPD Request or Response.
	 *
	 * @param espdStream An input stream hopefully containing a ESPD Request or Response
	 *
	 * @return An {@link EspdDocument} object coming out from the stream if it contained a valid ESPD Request or Response
	 * wrapped in an {@link Optional} or an empty {@link Optional} if the import was unsuccessful.
	 *
	 * @throws IOException in case the file cannot be read due to I/O issues
	 * @throws TedNoticeException if file contains Ted Notice XML
	 */
	public Optional<EspdDocument> importAmbiguousEspdFile(InputStream espdStream) throws IOException, TedNoticeException {
		// peek at the first bytes in the file to see if it is a ESPD Request or Response
		try (BufferedInputStream bis = new BufferedInputStream(espdStream)) {
			int peekReadLimit = 80;
			bis.mark(peekReadLimit);
			byte[] peek = new byte[peekReadLimit];
			int bytesRead = bis.read(peek, 0, peekReadLimit - 1);
			if (bytesRead < 0) {
				return Optional.absent();
			}
			bis.reset(); // need to read from the beginning afterwards
			String firstBytes = new String(peek, "UTF-8");

			// decide how to read the uploaded file
			if (firstBytes.contains("ESPDResponse")) {
				return importEspdResponse(bis);
			} else if (firstBytes.contains("ESPDRequest")) {
				return importEspdRequest(bis);
			} else if (firstBytes.contains("ContractNotice") || firstBytes.contains("TED_EXPORT")) {
				throw new TedNoticeException();
			}
		}
		return Optional.absent();
	}

	/**
	 * Merge the data coming from a ESPD Request with data coming from a ESPD Response.
	 * <p>
	 * The new file shall include:
	 * <ul>
	 * <li>Information on the EO</li>
	 * <li>All mandatory Exclusion grounds</li>
	 * <li>Selection criteria that where asked for in the new ESPD request that were answered in the old ESPD response</li>
	 * </ul>
	 * </p>
	 *
	 * @param requestStream  An input stream hopefully containing a ESPD Request
	 * @param responseStream An input stream hopefully containing a ESPD Response
	 *
	 * @return An {@link EspdDocument} object coming out from the merging of the Request and Response
	 * wrapped in an {@link Optional} or an empty {@link Optional} if the import was unsuccessful.
	 */
	@SuppressWarnings("unchecked")
	public Optional<EspdDocument> mergeEspdRequestAndResponse(InputStream requestStream, InputStream responseStream) {
		try {
			JAXBElement<ESPDRequestType> requestElement = (JAXBElement<ESPDRequestType>) jaxb2Marshaller
					.unmarshal(new StreamSource(requestStream));
			ESPDRequestType requestType = requestElement.getValue();
			JAXBElement<ESPDResponseType> responseElement = (JAXBElement<ESPDResponseType>) jaxb2Marshaller
					.unmarshal(new StreamSource(responseStream));
			ESPDResponseType responseType = responseElement.getValue();
			return Optional.of(requestResponseMerger.mergeRequestAndResponse(requestType, responseType));
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
			return Optional.absent();
		}
	}
}
