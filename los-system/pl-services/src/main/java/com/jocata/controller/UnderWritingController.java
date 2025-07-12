package com.jocata.controller;

import com.jocata.service.UnderWritingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UnderWritingController {
    @Autowired
    private UnderWritingService underWritingService;
    @GetMapping("/evaluate")
    public ResponseEntity<String> evaluate(
            @RequestParam String applicationId,
            @RequestParam String loanAmount){
        try {
            String decision = underWritingService.evaluate(applicationId, loanAmount);
            return ResponseEntity.ok(decision);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error during underwriting: " + e.getMessage());
        }
    }
    @GetMapping("/test1")
    public String test1() {
        return "Controller is working";
    }
}
