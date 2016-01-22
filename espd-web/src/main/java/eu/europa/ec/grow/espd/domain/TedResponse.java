package eu.europa.ec.grow.espd.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

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
}
