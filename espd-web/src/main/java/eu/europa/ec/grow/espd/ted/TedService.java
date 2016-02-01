package eu.europa.ec.grow.espd.ted;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ratoico on 1/21/16 at 5:08 PM.
 */
@Service
@Slf4j
public class TedService {

    private final RestTemplate restTemplate;

    @Value("${ted.api.base.url}")
    private String tedUrl;

    @Value("${ted.api.user}")
    private String tedUser;

    @Value("${ted.api.password}")
    private String tedPassword;

    @Autowired
    TedService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TedResponse getContractNoticeInformation(TedRequest tedRequest) {
        if (StringUtils.isBlank(tedRequest.getReceptionId())) {
            return new TedResponse();
        }

        log.info("--- Calling TED  with reception id: '{}'.", tedRequest.getReceptionId());
        HttpEntity<String> request = new HttpEntity<>(createHeaders(tedUser, tedPassword));
        try {
            ResponseEntity<TedResponse> response = restTemplate
                    .exchange(tedUrl + "/" + tedRequest.getReceptionId(), HttpMethod.GET, request, TedResponse.class);
            return response.getBody();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return new TedResponse();
        }
    }

    private HttpHeaders createHeaders(final String username, final String password) {
        String plainCreds = username + ":" + password;
        byte[] base64CredsBytes = Base64.encodeBase64(plainCreds.getBytes());
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + base64Creds);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
