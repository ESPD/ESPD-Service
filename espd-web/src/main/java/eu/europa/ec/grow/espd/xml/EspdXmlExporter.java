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

import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.xml.request.exporting.UblRequestTypeTransformer;
import eu.europa.ec.grow.espd.xml.response.exporting.UblResponseTypeTransformer;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

/**
 * Class used to generate XML files containing ESPD Requests or Responses.
 *
 * Created by ratoico on 1/23/17.
 */
@Component
public class EspdXmlExporter {

	private final Jaxb2Marshaller jaxb2Marshaller;
	private final UblRequestTypeTransformer toEspdRequestTransformer;
	private final UblResponseTypeTransformer toEspdResponseTransformer;
	private final grow.names.specification.ubl.schema.xsd.espdrequest_1.ObjectFactory espdRequestObjectFactory;
	private final grow.names.specification.ubl.schema.xsd.espdresponse_1.ObjectFactory espdResponseObjectFactory;

	@Autowired
	EspdXmlExporter(Jaxb2Marshaller jaxb2Marshaller, UblRequestTypeTransformer toEspdRequestTransformer,
			UblResponseTypeTransformer toEspdResponseTransformer) {
		this.jaxb2Marshaller = jaxb2Marshaller;
		this.toEspdRequestTransformer = toEspdRequestTransformer;
		this.toEspdResponseTransformer = toEspdResponseTransformer;
		this.espdRequestObjectFactory = new grow.names.specification.ubl.schema.xsd.espdrequest_1.ObjectFactory();
		this.espdResponseObjectFactory = new grow.names.specification.ubl.schema.xsd.espdresponse_1.ObjectFactory();
	}

	/**
	 * Create a {@link ESPDRequestType} from the provided {@link EspdDocument} and marshals it
	 * to the output stream.
	 *
	 * @param espdDocument The ESPD document that will be written out
	 *
	 * @return The stream where the XML representation will be written out
	 */
	public ByteArrayOutputStream generateEspdRequest(EspdDocument espdDocument) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ESPDRequestType espdRequestType = toEspdRequestTransformer.buildRequestType(espdDocument);
		StreamResult result = new StreamResult(out);
		jaxb2Marshaller.marshal(espdRequestObjectFactory.createESPDRequest(espdRequestType), result);
		return out;
	}

	/**
	 * Create a {@link ESPDRequestType} from the provided {@link EspdDocument} and marshals it
	 * as a {@link StringWriter}.
	 *
	 * @param espdDocument The ESPD document that will be written out
	 * @param sw           The place where the XML representation will be written out
	 */
	public void generateEspdRequest(EspdDocument espdDocument, StringWriter sw) {
		ESPDRequestType espdRequestType = toEspdRequestTransformer.buildRequestType(espdDocument);
		StreamResult result = new StreamResult(sw);

		jaxb2Marshaller.marshal(espdRequestObjectFactory.createESPDRequest(espdRequestType), result);
	}

	/**
	 * Create a {@link ESPDResponseType} from the provided {@link EspdDocument} and marshals it
	 * to the output stream.
	 *
	 * @param espdDocument The ESPD document that will be written out
	 *
	 * @return The stream where the XML representation will be written out
	 */
	public ByteArrayOutputStream generateEspdResponse(EspdDocument espdDocument) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ESPDResponseType espdResponseType = toEspdResponseTransformer.buildResponseType(espdDocument);
		StreamResult result = new StreamResult(out);
		jaxb2Marshaller.marshal(espdResponseObjectFactory.createESPDResponse(espdResponseType), result);
		return out;
	}

	/**
	 * Create a {@link ESPDResponseType} from the provided {@link EspdDocument} and marshals it
	 * to the output stream.
	 *
	 * @param espdDocument The ESPD document that will be written out
	 * @param sw           The place where the XML representation will be written out
	 */
	public void generateEspdResponse(EspdDocument espdDocument, StringWriter sw) {
		ESPDResponseType espdResponseType = toEspdResponseTransformer.buildResponseType(espdDocument);
		StreamResult result = new StreamResult(sw);
		jaxb2Marshaller.marshal(espdResponseObjectFactory.createESPDResponse(espdResponseType), result);
	}
}
