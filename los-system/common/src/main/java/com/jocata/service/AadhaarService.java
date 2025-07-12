package com.jocata.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public interface AadhaarService {
    ResponseEntity<String> getAadhaarDetailsByAadhaar(String aadhaarNumber);

    void setRestTemplate(RestTemplate restTemplate);
}
