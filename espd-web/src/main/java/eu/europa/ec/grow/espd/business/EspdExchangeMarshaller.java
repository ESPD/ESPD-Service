package eu.europa.ec.grow.espd.business;

import eu.europa.ec.grow.espd.domain.EspdDocument;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;
import java.io.StringWriter;

/**
 * Created by vigi on 11/11/15:3:22 PM.
 */
@Component
public class EspdExchangeMarshaller {

    private final Jaxb2Marshaller jaxb2Marshaller;
    private final EspdDocumentToEspdRequestTransformer toEspdRequestTransformer;
    private final ObjectFactory objectFactory;

    @Autowired
    EspdExchangeMarshaller(final Jaxb2Marshaller jaxb2Marshaller,
            final EspdDocumentToEspdRequestTransformer toEspdRequestTransformer) {
        this.jaxb2Marshaller = jaxb2Marshaller;
        this.toEspdRequestTransformer = toEspdRequestTransformer;
        this.objectFactory = new ObjectFactory();
    }

    public void generateEspdRequest(EspdDocument espdDocument, OutputStream out) {
        ESPDRequestType espdRequestType = toEspdRequestTransformer.apply(espdDocument);
        StreamResult result = new StreamResult(out);
        jaxb2Marshaller.marshal(objectFactory.createESPDRequest(espdRequestType), result);
    }

    public void generateEspdRequest(EspdDocument espdDocument, StringWriter sw) throws JAXBException {
        ESPDRequestType espdRequestType = toEspdRequestTransformer.apply(espdDocument);
        StreamResult result = new StreamResult(sw);

        jaxb2Marshaller.marshal(objectFactory.createESPDRequest(espdRequestType), result);
    }
}
