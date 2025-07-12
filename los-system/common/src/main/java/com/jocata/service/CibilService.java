package com.jocata.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public interface CibilService {
    ResponseEntity<String> getCiibilByPanNumber(String panNumber);
    void setRestTemplate(RestTemplate restTemplate);
}
