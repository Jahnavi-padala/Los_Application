package com.jocata.form;



public class LoanApplicationForm {

    private String  applicationId;
    private String loanAmount;
    private  String loanType;
    private String applicateDate;
    private  String approvedAmount;
    private String isActive;
    private String status;
    private String customerId ;
    private String termMonths;
    private String loanPurpose;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getApplicateDate() {
        return applicateDate;
    }

    public void setApplicateDate(String applicateDate) {
        this.applicateDate = applicateDate;
    }

    public String getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(String approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(String termMonths) {
        this.termMonths = termMonths;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }
}
