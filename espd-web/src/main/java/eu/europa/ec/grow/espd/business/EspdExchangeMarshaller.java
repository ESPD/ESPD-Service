package eu.europa.ec.grow.espd.business;

import eu.europa.ec.grow.espd.domain.EspdDocument;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;
import java.io.StringWriter;

/**
 * Class that transforms a {@link EspdDocument} into a {@link ESPDRequestType} and marshals the result into a XML
 * representation to different outputs.
 * <p/>
 * Created by vigi on 11/11/15:3:22 PM.
 */
@Component
public class EspdExchangeMarshaller {

    private final Jaxb2Marshaller jaxb2Marshaller;
    private final EspdDocumentToEspdRequestTransformer toEspdRequestTransformer;
    private final grow.names.specification.ubl.schema.xsd.espdrequest_1.ObjectFactory espdRequestObjectFactory;
    private final grow.names.specification.ubl.schema.xsd.espdresponse_1.ObjectFactory espdResponseObjectFactory;

    @Autowired
    EspdExchangeMarshaller(final Jaxb2Marshaller jaxb2Marshaller,
            final EspdDocumentToEspdRequestTransformer toEspdRequestTransformer) {
        this.jaxb2Marshaller = jaxb2Marshaller;
        this.toEspdRequestTransformer = toEspdRequestTransformer;
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
        ESPDRequestType espdRequestType = toEspdRequestTransformer.apply(espdDocument);
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
        ESPDRequestType espdRequestType = toEspdRequestTransformer.apply(espdDocument);
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
        ESPDResponseType espdResponseType  = new ESPDResponseType();
        StreamResult result = new StreamResult(out);
        jaxb2Marshaller.marshal(espdResponseObjectFactory.createESPDResponse(espdResponseType), result);
    }
}
