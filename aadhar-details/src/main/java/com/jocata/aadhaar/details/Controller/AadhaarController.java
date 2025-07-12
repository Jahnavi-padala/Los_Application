package com.jocata.aadhaar.details.Controller;

import com.jocata.aadhaar.details.Service.AadhaarService;
import com.jocata.aadhaar.details.form.AadhaarDetails;
import com.jocata.aadhaar.details.form.AadhaarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;


@RestController
@RequestMapping("/api/v1")

public class AadhaarController {
    @Autowired
    AadhaarService aadhaarService;
    @GetMapping("/getAadhaarDetails")
    public AadhaarResponse getAadhaarDetails(@RequestParam String aadhaarNumber) {
        AadhaarDetails details = null;
        try {
            details = aadhaarService.getAadhaarDetails(aadhaarNumber);
            if (details != null) {
                return new AadhaarResponse(details, "success", "Aadhaar details found");
            } else {
                return new AadhaarResponse(null, "error", "Aadhaar not found");
            }
        } catch (Exception e) {
            return new AadhaarResponse(null, "error", "Something went wrong");
        }
    }
}
