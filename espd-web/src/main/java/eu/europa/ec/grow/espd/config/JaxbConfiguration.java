package eu.europa.ec.grow.espd.config;

import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.bind.Marshaller;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vigi on 11/12/15:5:41 PM.
 */
@Configuration
public class JaxbConfiguration {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan(ESPDRequestType.class.getPackage().getName(),
                ESPDResponseType.class.getPackage().getName());
        Map<String, Object> map = new HashMap<>(2);
        map.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxb2Marshaller.setMarshallerProperties(map);
        return jaxb2Marshaller;
    }

}
