package com.jocata.controller;

import com.jocata.form.LoanForm;
import com.jocata.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping("/createApplication")
    public LoanForm createApplication(@RequestBody LoanForm loanRequestForm) {

        try {
            return loanApplicationService.createApplication(loanRequestForm);
        } catch (Exception e) {
            e.printStackTrace();



            LoanForm errorForm = new LoanForm();

            return errorForm;
        }
    }

    @GetMapping("/getApplicationById/{applicationId}")
    public LoanForm getApplicationById(@PathVariable Integer applicationId) {
        try {
            return loanApplicationService.getApplicationById(applicationId);
        } catch (Exception e) {
            e.printStackTrace();


            LoanForm errorForm = new LoanForm();
            return errorForm;
        }
    }

}
