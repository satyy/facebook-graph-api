package com.satyy.fb.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.satyy.fb.config.CovidApiConfig;
import com.satyy.fb.exception.ServerException;
import com.satyy.fb.model.CovidStateData;

import java.util.List;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class CovidDetailService {

    @Autowired
    private CovidApiConfig apiConfig;

    private ObjectMapper mapper;

    @PostConstruct
    private void init() {
        mapper = new ObjectMapper();
    }

    public List<CovidStateData> getStateData() throws ServerException {
        log.info("Processing state wise covid data request...");
        final RestTemplate restTemplate = new RestTemplate();
        try {
            final ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiConfig.getEndpoint(), String.class);
            log.info("Response code : {}", responseEntity.getStatusCode().toString());
            final List<CovidStateData> stateData = mapper.readValue(responseEntity.getBody(), new TypeReference<>() {
            });
            log.info("Fetched Covid data from server...");
            return stateData;
        } catch (RestClientResponseException e) {
            log.error("Error fetching covid details from server. Status Code {}, Msg {}", e.getRawStatusCode(), e.getResponseBodyAsString());
            e.printStackTrace();
            throw new ServerException("Unable to fetch covid details at this time");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Could not fetch details from server. Error msg: {}", e.getMessage());
            throw new ServerException("Unable to fetch covid details at this time");
        }
    }
}
