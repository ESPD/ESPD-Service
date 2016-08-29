/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

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

    @Value("${piwik.enabled:false}")
    private boolean piwikEnabled;

    @Value("${piwik.id:2}")
    private Integer piwikId;

    @Value("${piwik.server:https://webgate.ec.europa.eu/pwar/piwik.php}")
    private String piwikServer;

    @Value("${ecertis.criterion.url:https://www.development.ec.europa.eu/growth/tools-databases/ecertisrest/criteria/espd/[uuid]/?countryFilter=[country]&lang=[lang]}")
    private String ecertisCriterionURL;

    @Value("${espd.environment:false}")
    private boolean espdEnvironment;
    
    @Autowired
    private Environment environment;

    public String getActiveProfile() {
        if (ArrayUtils.isNotEmpty(environment.getActiveProfiles())) {
            return Arrays.toString(environment.getActiveProfiles());
        }
        return Arrays.toString(environment.getDefaultProfiles());
    }
}
