package eu.europa.ec.grow.espd.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.WebApplicationInitializer;

import javax.xml.bind.Marshaller;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vigi on 10/20/15:5:17 PM.
 */
@SpringBootApplication
@ComponentScan("eu.europa.ec.grow.espd")
public class EspdApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EspdApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EspdApplication.class, args);
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan(EspdDocument.class.getPackage().getName(),
                ESPDRequestType.class.getPackage().getName(), ESPDResponseType.class.getPackage().getName());
        Map<String, Object> map = new HashMap<>(2);
        map.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxb2Marshaller.setMarshallerProperties(map);
        return jaxb2Marshaller;
    }
}