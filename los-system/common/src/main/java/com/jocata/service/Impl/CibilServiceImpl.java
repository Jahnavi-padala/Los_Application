package com.jocata.service.Impl;

import com.jocata.service.CibilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class CibilServiceImpl implements CibilService {
    @Autowired
    private RestTemplate restTemplate;
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public ResponseEntity<String> getCiibilByPanNumber(String panNumber)
    {
        String url="http://localhost:8083/cibil/api/v1/getCibilByPanNumber?panNumber="+panNumber;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response;


    }
}
