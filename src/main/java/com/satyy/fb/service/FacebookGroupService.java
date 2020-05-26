package com.satyy.fb.service;

import com.satyy.fb.config.FacebookGroupConfig;
import com.satyy.fb.exception.ServerException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class FacebookGroupService {

    @Autowired
    private FacebookGroupConfig config;

    public void publishToGroup(final String message) throws ServerException {
        final String uri = config.getEndpoint().concat(config.getNodeId()).concat("/feed");

        final RestTemplate restTemplate = new RestTemplate();
        try {
            final ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, getGroupRequestEntity(message), String.class);
            log.info("Response code from fb api {}, Message {}", responseEntity.getStatusCode(), responseEntity.getBody());
        } catch (RestClientResponseException e) {
            log.error("Error posting message to group. Status Code {}, Msg {}", e.getRawStatusCode(), e.getResponseBodyAsString());
            throw new ServerException("Unable to post message to group");
        } catch (Exception e) {
            log.error("Could not post message to fb group. Error msg: {}", e.getMessage());
            throw new ServerException("Unable to post message to group at this time");
        }
    }

    private HttpHeaders getHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return headers;
    }

    private HttpEntity<MultiValueMap<String, String>> getGroupRequestEntity(final String message) {
        final MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("message", message);
        map.add("access_token", config.getAccessToken());

        return new HttpEntity<>(map, getHeaders());
    }
}
