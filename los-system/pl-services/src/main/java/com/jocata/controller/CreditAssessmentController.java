package com.jocata.controller;

import com.jocata.service.CreditAsseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")

public class CreditAssessmentController {
    @Autowired
    private CreditAsseService creditAsseService;
    @GetMapping("/evaluateLoan/{loanApplicationId}")
    public ResponseEntity<String> evaluateLoan(@PathVariable String loanApplicationId) {
        try {
            String result = creditAsseService.evaluateLoan(loanApplicationId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error evaluating eligibility: " + e.getMessage());
        }

    }
    @GetMapping("/test")
    public String test() {
        return "Controller is working";
    }


}
