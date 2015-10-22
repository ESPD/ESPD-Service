package eu.grow.espd.config;

import eu.grow.espd.util.EspdMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.WebApplicationInitializer;

import javax.sql.DataSource;

/**
 * Created by vigi on 10/20/15:5:17 PM.
 */
@SpringBootApplication
@ComponentScan("eu.grow.espd")
public class EspdApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Autowired
    private DataSource dataSource;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EspdApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EspdApplication.class, args);
    }

    @Bean
    MessageSource messageSource(JdbcTemplate jdbcTemplate) {
        return new EspdMessageSource(jdbcTemplate);
    }

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }
}