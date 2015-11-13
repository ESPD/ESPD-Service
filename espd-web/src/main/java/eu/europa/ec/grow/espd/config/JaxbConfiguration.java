package eu.europa.ec.grow.espd.config;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import eu.europa.ec.grow.espd.domain.EspdDocument;
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
        jaxb2Marshaller.setPackagesToScan(EspdDocument.class.getPackage().getName(),
                ESPDRequestType.class.getPackage().getName(), ESPDResponseType.class.getPackage().getName());
        Map<String, Object> map = new HashMap<>(2);
        map.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        map.put("com.sun.xml.bind.namespacePrefixMapper", new NamespacePrefixMapperImpl());
        jaxb2Marshaller.setMarshallerProperties(map);
        return jaxb2Marshaller;
    }

    public static final class NamespacePrefixMapperImpl extends NamespacePrefixMapper {

        @Override
        public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
            if ("urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2".equals(namespaceUri)) {
                return "cbc";
            } else if ("urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2".equals(namespaceUri)) {
                return "cac";
            } else if ("urn:isa:names:specification:ubl:schema:xsd:CCV-CommonAggregateComponents-1"
                    .equals(namespaceUri)) {
                return "ccv";
            } else if ("urn:grow:names:specification:ubl:schema:xsd:ESPDRequest-1".equals(namespaceUri)) {
                return "";
            }
            return suggestion;
        }
    }
}
