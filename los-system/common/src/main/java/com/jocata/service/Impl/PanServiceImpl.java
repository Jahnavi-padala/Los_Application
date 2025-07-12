package com.jocata.service.Impl;

import com.jocata.responseform.PanResponse;
import com.jocata.service.PanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class PanServiceImpl implements PanService {
    @Autowired
    private RestTemplate restTemplate;
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public ResponseEntity<String> getPanDetailsByPan(String panNumber)
    {
        String url="http://localhost:8080/panservice/api/v1/getPanDetails?panNumber="+panNumber;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response;


    }
}
