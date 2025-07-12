package com.jocata.form;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanForm {
    private CustomerForm customer;
    private LoanApplicationForm loanDetails;
    private IncomeDetailsForm incomeDetails;

    public CustomerForm getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerForm customer) {
        this.customer = customer;
    }

    public LoanApplicationForm getLoanDetails() {
        return loanDetails;
    }

    public void setLoanDetails(LoanApplicationForm loanDetails) {
        this.loanDetails = loanDetails;
    }

    public IncomeDetailsForm getIncomeDetails() {
        return incomeDetails;
    }

    public void setIncomeDetails(IncomeDetailsForm incomeDetails) {
        this.incomeDetails = incomeDetails;
    }
}
