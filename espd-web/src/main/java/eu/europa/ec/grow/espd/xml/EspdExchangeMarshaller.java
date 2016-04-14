package eu.europa.ec.grow.espd.xml;

import com.google.common.base.Optional;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.xml.request.exporting.UblRequestTypeTransformer;
import eu.europa.ec.grow.espd.xml.request.importing.UblRequestToEspdDocumentTransformer;
import eu.europa.ec.grow.espd.xml.response.exporting.UblResponseTypeTransformer;
import eu.europa.ec.grow.espd.xml.response.importing.UblRequestResponseMerger;
import eu.europa.ec.grow.espd.xml.response.importing.UblResponseToEspdDocumentTransformer;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * Class that can perform XML marshalling and unmarshalling from {@link ESPDRequestType} and {@link ESPDResponseType}
 * to a {@link EspdDocument}.
 * <p/>
 * Created by vigi on 11/11/15:3:22 PM.
 */
@Component
@Slf4j
public class EspdExchangeMarshaller {

    private final Jaxb2Marshaller jaxb2Marshaller;
    private final UblRequestTypeTransformer toEspdRequestTransformer;
    private final UblRequestToEspdDocumentTransformer requestToEspdDocumentTransformer;
    private final UblResponseToEspdDocumentTransformer responseToEspdDocumentTransformer;
    private final UblResponseTypeTransformer toEspdResponseTransformer;
    private final UblRequestResponseMerger requestResponseMerger;
    private final grow.names.specification.ubl.schema.xsd.espdrequest_1.ObjectFactory espdRequestObjectFactory;
    private final grow.names.specification.ubl.schema.xsd.espdresponse_1.ObjectFactory espdResponseObjectFactory;

    @Autowired
    EspdExchangeMarshaller(Jaxb2Marshaller jaxb2Marshaller,
            UblRequestTypeTransformer toEspdRequestTransformer,
            UblRequestToEspdDocumentTransformer requestToEspdDocumentTransformer,
            UblResponseToEspdDocumentTransformer responseToEspdDocumentTransformer,
            UblResponseTypeTransformer toEspdResponseTransformer, UblRequestResponseMerger requestResponseMerger) {
        this.jaxb2Marshaller = jaxb2Marshaller;
        this.toEspdRequestTransformer = toEspdRequestTransformer;
        this.requestToEspdDocumentTransformer = requestToEspdDocumentTransformer;
        this.responseToEspdDocumentTransformer = responseToEspdDocumentTransformer;
        this.toEspdResponseTransformer = toEspdResponseTransformer;
        this.requestResponseMerger = requestResponseMerger;
        this.espdRequestObjectFactory = new grow.names.specification.ubl.schema.xsd.espdrequest_1.ObjectFactory();
        this.espdResponseObjectFactory = new grow.names.specification.ubl.schema.xsd.espdresponse_1.ObjectFactory();
    }

    /**
     * Create a {@link ESPDRequestType} from the provided {@link EspdDocument} and marshals it
     * to the output stream.
     *
     * @param espdDocument The ESPD document that will be written out
     * @param out          The place where the XML representation will be written out
     */
    public void generateEspdRequest(EspdDocument espdDocument, OutputStream out) {
        ESPDRequestType espdRequestType = toEspdRequestTransformer.buildRequestType(espdDocument);
        StreamResult result = new StreamResult(out);
        jaxb2Marshaller.marshal(espdRequestObjectFactory.createESPDRequest(espdRequestType), result);
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
     * @param out          The place where the XML representation will be written out
     */
    public void generateEspdResponse(EspdDocument espdDocument, OutputStream out) {
        ESPDResponseType espdResponseType = toEspdResponseTransformer.buildResponseType(espdDocument);
        StreamResult result = new StreamResult(out);
        jaxb2Marshaller.marshal(espdResponseObjectFactory.createESPDResponse(espdResponseType), result);
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
            return Optional.of(requestToEspdDocumentTransformer.apply(requestType));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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
            log.error(e.getMessage(), e);
            return Optional.absent();
        }
    }

    /**
     * Read from an input stream that should contain a {@link ESPDRequestType} or {@link ESPDResponseType}
     * and convert it into a {@link EspdDocument}. It is not known beforehand if the content
     * belongs to a ESPD Request or Response.
     *
     * @param is An input stream hopefully containing a ESPD Request or Response
     *
     * @return An {@link EspdDocument} object coming out from the stream if it contained a valid ESPD Request or Response
     * wrapped in an {@link Optional} or an empty {@link Optional} if the import was unsuccessful.
     *
     * @throws IOException
     */
    public Optional<EspdDocument> importAmbiguousEspdFile(InputStream is) throws IOException {
        // peek at the first bytes in the file to see if it is a ESPD Request or Response
        try (BufferedInputStream bis = new BufferedInputStream(is)) {
            int peekReadLimit = 80;
            bis.mark(peekReadLimit);
            byte[] peek = new byte[peekReadLimit];
            int bytesRead = bis.read(peek, 0, peekReadLimit - 1);
            if (bytesRead < 0) {
                return null;
            }
            bis.reset(); // need to read from the beginning afterwards
            String firstBytes = new String(peek, "UTF-8");

            // decide how to read the uploaded file
            if (firstBytes.contains("ESPDResponse")) {
                return importEspdResponse(bis);
            } else if (firstBytes.contains("ESPDRequest")) {
                return importEspdRequest(bis);
            }
        }
        return Optional.absent();
    }

    /**
     * Merge the data coming from a ESPD Request with data coming from a ESPD Response.
     * <p>
     * The new file shall include:
     * <ul>
     *     <li>Information on the EO</li>
     *     <li>All mandatory Exclusion grounds</li>
     *     <li>Selection criteria that where asked for in the new ESPD request that were answered in the old ESPD response</li>
     * </ul>
     * </p>
     *
     * @param requestStream An input stream hopefully containing a ESPD Request
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
            log.error(e.getMessage(), e);
            return Optional.absent();
        }
    }
}
