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

package eu.europa.ec.grow.espd.ted;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.commons.collections.MapUtils;

import java.util.Map;

/**
 * Created by ratoico on 1/21/16 at 5:08 PM.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TedResponse {

    private Map<String, TedNotice> info;

    private String noDocOjs;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class TedNotice {

        private String officialName;

        private String title;

        private String shortDescription;

        private String referenceNumber;

        private String tedUrl;

    }

    public TedNotice getFirstNotice() {
        if (MapUtils.isEmpty(info)) {
            return new TedNotice();
        }
        return info.entrySet().iterator().next().getValue();
    }
}
