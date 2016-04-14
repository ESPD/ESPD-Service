package eu.europa.ec.grow.espd.config;

import ac.simons.spring.boot.wro4j.Wro4jAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * Created by ratoico on 12/10/15 at 10:14 AM.
 */

@Profile("default")
@Configuration
class Wro4jConfig extends Wro4jAutoConfiguration {

    // only used for development ('default' profile) when we need the Wro4J Filter

}
