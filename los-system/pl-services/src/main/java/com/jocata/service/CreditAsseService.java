package com.jocata.service;

import com.jocata.entity.LoanApplications;

import java.math.BigDecimal;

public interface CreditAsseService {
    String evaluateLoan(String loanApplicationIdStr);
    String evaluateEligibilityAndDecision(LoanApplications loanApplication, BigDecimal interestRate, BigDecimal availForNew);
    BigDecimal getInterestRateByApplicationId(String applicationIdStr);
    BigDecimal calculateEligibleAmount(String applicationIdStr);
    BigDecimal calculatePrincipal(BigDecimal emi, BigDecimal annualRate, int tenureMonths);
    BigDecimal evaluateLoanEligibility(String applicationIdStr);


}
