package eu.europa.ec.grow.espd.business.ted;

import eu.europa.ec.grow.espd.domain.TedResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ratoico on 1/21/16 at 5:08 PM.
 */
@Service
public class TedService {

    private final RestTemplate restTemplate;

    @Value("ted.api.base.url")
    private String tedUrl;

    @Autowired
    TedService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TedResponse getContractNoticeInformation(String contractNoticeId) {
        HttpEntity<String> request = new HttpEntity<String>(createHeaders("user", "pass"));
        ResponseEntity<TedResponse> response = restTemplate
                .exchange(tedUrl, HttpMethod.GET, request, TedResponse.class);
        TedResponse account = response.getBody();
        return account;
    }

    private HttpHeaders createHeaders(final String username, final String password) {
        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        return headers;
    }
}
