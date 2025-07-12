package com.jocata.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public interface PanService {
    ResponseEntity<String> getPanDetailsByPan(String panNumber);
    void setRestTemplate(RestTemplate restTemplate);
}
