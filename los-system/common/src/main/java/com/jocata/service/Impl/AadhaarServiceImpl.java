package com.jocata.service.Impl;

import com.jocata.service.AadhaarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class AadhaarServiceImpl implements AadhaarService {
    @Autowired
    private RestTemplate restTemplate;
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public ResponseEntity<String> getAadhaarDetailsByAadhaar(String aadhaarNumber)
    {
        String url="http://localhost:8081/aadhardetails/api/v1/getAadhaarDetails?aadhaarNumber="+aadhaarNumber;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response;


    }
}
