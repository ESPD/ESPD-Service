package eu.europa.ec.grow.espd.config;

import ro.isdc.wro.manager.factory.ConfigurableWroManagerFactory;

import java.util.Properties;

/**
 * Taken from https://github.com/sbuettner/spring-boot-autoconfigure-wro4j
 *
 * Created by ratoico on 12/10/15 at 10:16 AM.
 */
class SimpleWroManagerFactory extends ConfigurableWroManagerFactory {

    private final Properties configProperties;

    public SimpleWroManagerFactory(Properties configProperties) {
        this.configProperties = configProperties;
    }

    @Override
    protected Properties newConfigProperties() {
        return configProperties;
    }

}