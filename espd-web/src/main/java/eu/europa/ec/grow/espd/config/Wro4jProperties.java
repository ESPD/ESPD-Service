package eu.europa.ec.grow.espd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p></p>
 * Taken from https://github.com/sbuettner/spring-boot-autoconfigure-wro4j.
 * <p/>
 * You can override these properties in application-${profile}.properties.
 * <p></p>
 * They should be linked to the values defined in this class: {@link ro.isdc.wro.config.jmx.ConfigConstants}.
 * <p></p>
 * <p/>
 * Created by ratoico on 12/10/15 at 10:15 AM.
 */
@ConfigurationProperties("wro4j")
@Data
class Wro4jProperties {

    private boolean disableCache;

    private boolean debug = false;

    private int cacheUpdatePeriod = 0;

    private int resourceWatcherUpdatePeriod = 0;

    private String urlPattern = "/static/*";

    private String uriLocators = "servletContext,classpath,uri";

    private String preProcessors = "fallbackCssDataUri,cssUrlRewriting,cssImport,semicolonAppender";

    // TODO investigate css compression that works much faster (maybe use wro4j maven build plugin)
    private String postProcessors = "cssVariables,jsMin";

    private String groovyResourceName = "wro.groovy";

    private String hashStrategy = "MD5";

    private String namingStrategy = "hashEncoder-CRC32";

    private boolean cacheGzippedContent = true;

    private boolean parallelProcessing = false;

    private boolean minimizeEnabled = true;
    

}