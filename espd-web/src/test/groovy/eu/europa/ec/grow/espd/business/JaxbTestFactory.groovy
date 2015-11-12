package eu.europa.ec.grow.espd.business

import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType
import org.springframework.oxm.jaxb.Jaxb2Marshaller

/**
 * Created by vigi on 11/12/15:11:15 AM.
 */
class JaxbTestFactory {

    Jaxb2Marshaller buildJaxb2Marshaller() {
        def jaxb2Marshaller = new Jaxb2Marshaller()
        jaxb2Marshaller.setPackagesToScan(ESPDRequestType.class.getPackage().getName(),
                ESPDResponseType.class.getPackage().getName())
        jaxb2Marshaller.setMarshallerProperties(["jaxb.formatted.output": true])
        return jaxb2Marshaller
    }
}
