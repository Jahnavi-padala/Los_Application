package com.jocata.cibil.controller;

import com.jocata.cibil.form.CreditReportRequestForm;
import com.jocata.cibil.form.CreditReportResponseForm;
import com.jocata.cibil.service.CibilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")

public class CibilController {
    @Autowired
    private CibilService cibilService;
    @PostMapping("/createCibil")
    public CreditReportResponseForm createCibil(@RequestBody CreditReportRequestForm request) {
        return cibilService.createCibil(request);
    }
    @GetMapping("/getCibilByPanNumber")
    public CreditReportResponseForm getCibilByPanNumber(@RequestParam String panNumber) {
        return cibilService.getCibilByPanNumber(panNumber);
    }
}
