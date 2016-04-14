package eu.europa.ec.grow.espd.util;

import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by vigi on 10/21/15:2:28 PM.
 */
@Data
@Component
public class EspdConfiguration {

    @Value("${info.build.version:N/A}")
    private String buildVersion;

    @Value("${last.build.date:N/A}")
    private String lastBuildDate;

    @Value("${piwik.id:2}")
    private Integer piwikId;

    @Value("${piwik.server:https://webgate.ec.europa.eu/pwar/piwik.php}")
    private String piwikServer;

    @Autowired
    private Environment environment;

    public String getActiveProfile() {
        if (ArrayUtils.isNotEmpty(environment.getActiveProfiles())) {
            return Arrays.toString(environment.getActiveProfiles());
        }
        return Arrays.toString(environment.getDefaultProfiles());
    }
}
