package com.jocata.service;

import com.jocata.form.LoanForm;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoanApplicationService {
    LoanForm createApplication(@RequestBody LoanForm loanRequestForm);
    LoanForm getApplicationById(Integer applicationId);
    LoanForm buildErrorLoanForm(String errorMessage);
}
