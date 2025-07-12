package com.jocata.controller;

import com.jocata.service.DataVerificationService;
import com.jocata.service.Impl.DataVerificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")

public class DataVerificationController {


    @Autowired
    private DataVerificationService dataVerificationService;

    @GetMapping("/getCustomerData/{loanApplicationId}")
    public ResponseEntity<String> getCustomerData(@PathVariable Integer loanApplicationId) {
        try {
            dataVerificationService.getCustomerData(loanApplicationId);
            return ResponseEntity.ok("PAN details verified and assigned successfully for loanApplicationId: " + loanApplicationId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Verification failed: " + e.getMessage());
        }
    }


}
