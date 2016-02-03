package eu.europa.ec.grow.espd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ro.isdc.wro.config.jmx.ConfigConstants;
import ro.isdc.wro.http.ConfigurableWroFilter;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.model.resource.locator.factory.ConfigurableLocatorFactory;
import ro.isdc.wro.model.resource.processor.factory.ConfigurableProcessorsFactory;
import ro.isdc.wro.model.resource.support.hash.ConfigurableHashStrategy;
import ro.isdc.wro.model.resource.support.naming.ConfigurableNamingStrategy;

import java.util.Properties;

/**
 * Taken from https://github.com/sbuettner/spring-boot-autoconfigure-wro4j.
 *
 * Created by ratoico on 12/10/15 at 10:14 AM.
 */

@Configuration
@EnableConfigurationProperties(Wro4jProperties.class)
@Profile("default")
class Wro4jConfig {

    @Autowired
    private Wro4jProperties wro4jProperties;

    @Bean(destroyMethod = "destroy")
    ConfigurableWroFilter wroFilter(WroManagerFactory wroManagerFactory) {
        ConfigurableWroFilter wroFilter = new ConfigurableWroFilter();
        wroFilter.setProperties(wroFilterProperties());
        wroFilter.setWroManagerFactory(wroManagerFactory);
        return wroFilter;
    }

    private Properties wroFilterProperties() {
        Properties properties = new Properties();
        properties.put(ConfigConstants.debug.name(), String.valueOf(wro4jProperties.isDebug()));
        properties.put(ConfigConstants.disableCache.name(), String.valueOf(wro4jProperties.isDisableCache()));
        properties.put(ConfigConstants.cacheUpdatePeriod.name(), String.valueOf(wro4jProperties.getCacheUpdatePeriod()));
        properties.put(ConfigConstants.resourceWatcherUpdatePeriod.name(), String.valueOf(wro4jProperties.getResourceWatcherUpdatePeriod()));
        properties.put(ConfigConstants.cacheGzippedContent.name(), String.valueOf(wro4jProperties.isCacheGzippedContent()));
        properties.put(ConfigConstants.parallelPreprocessing.name(), String.valueOf(wro4jProperties.isParallelProcessing()));
        return properties;
    }

    @Bean
    FilterRegistrationBean wro4jFilterRegistration(ConfigurableWroFilter wroFilter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(wroFilter);
        filterRegistrationBean.addUrlPatterns(wro4jProperties.getUrlPattern());
        return filterRegistrationBean;
    }

    @ConditionalOnMissingBean(WroManagerFactory.class)
    @Bean(destroyMethod = "destroy")
    WroManagerFactory fallbackWroManagerFactory() {
        return new SimpleWroManagerFactory(wroManagerFactoryProperties());
    }

    private Properties wroManagerFactoryProperties() {
        Properties properties = new Properties();
        properties.put(ConfigurableLocatorFactory.PARAM_URI_LOCATORS, wro4jProperties.getUriLocators());
        properties.put(ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS, wro4jProperties.getPreProcessors());
        properties.put(ConfigurableProcessorsFactory.PARAM_POST_PROCESSORS, wro4jProperties.getPostProcessors());
        properties.put(ConfigurableNamingStrategy.KEY, wro4jProperties.getNamingStrategy());
        properties.put(ConfigurableHashStrategy.KEY, wro4jProperties.getHashStrategy());
        return properties;
    }

}
