package com.jocata.cibil.form;

import java.util.List;

public class CreditReportResponseForm {
    private String reportId;
    private String generatedOn;
    private CustomerResponseForm customer;
    private CibilScoreResponseForm cibilScore;
    private List<AccountsResponseForm> accounts;
    private List<EnquiriesResponseForm> enquiries;
    private List<String> remarks;


    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(String generatedOn) {
        this.generatedOn = generatedOn;
    }

    public CustomerResponseForm getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponseForm customer) {
        this.customer = customer;
    }

    public CibilScoreResponseForm getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(CibilScoreResponseForm cibilScore) {
        this.cibilScore = cibilScore;
    }

    public List<AccountsResponseForm> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountsResponseForm> accounts) {
        this.accounts = accounts;
    }

    public List<EnquiriesResponseForm> getEnquiries() {
        return enquiries;
    }

    public void setEnquiries(List<EnquiriesResponseForm> enquiries) {
        this.enquiries = enquiries;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
