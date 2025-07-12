package com.jocata;

import com.jocata.service.AadhaarService;
import com.jocata.service.CibilService;
import com.jocata.service.Impl.AadhaarServiceImpl;
import com.jocata.service.Impl.CibilServiceImpl;
import com.jocata.service.Impl.PanServiceImpl;
import com.jocata.service.PanService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder().build();

        PanService panService=new PanServiceImpl();
        panService.setRestTemplate(restTemplate);

        String pan = "XYZPD1234K";
        ResponseEntity<String> response = panService.getPanDetailsByPan(pan);

        System.out.println("Response: " + response.getBody());
       AadhaarService aadhaarService= new AadhaarServiceImpl();
       aadhaarService.setRestTemplate(restTemplate);
        String aadhaar="123412901234" ;
      ResponseEntity<String > res = aadhaarService.getAadhaarDetailsByAadhaar(aadhaar);
        System.out.println("Response: " + res.getBody());
        CibilService cibilService=new CibilServiceImpl();
        cibilService.setRestTemplate(restTemplate);
        String panNumber="XYZJD1234K";
        ResponseEntity<String > resp = cibilService.getCiibilByPanNumber(panNumber);
        System.out.println("Response: " + resp.getBody());

    }
}