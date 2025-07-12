package com.jocata.cibil.form;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CreditReportRequestForm {
    private String reportId;
    private String generatedOn;
    @NotNull
    private CustomerRequestForm customer;
    private CibilScoreRequestForm cibilScore;
    private List<AccountsRequestForm> accounts;
    private List<EnquiriesRequestForm> enquiries;
    private List<RemarksRequestForm> remarks;


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

    public CustomerRequestForm getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerRequestForm customer) {
        this.customer = customer;
    }

    public CibilScoreRequestForm getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(CibilScoreRequestForm cibilScore) {
        this.cibilScore = cibilScore;
    }

    public List<AccountsRequestForm> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountsRequestForm> accounts) {
        this.accounts = accounts;
    }

    public List<EnquiriesRequestForm> getEnquiries() {
        return enquiries;
    }

    public void setEnquiries(List<EnquiriesRequestForm> enquiries) {
        this.enquiries = enquiries;
    }

    public List<RemarksRequestForm> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<RemarksRequestForm> remarks) {
        this.remarks = remarks;
    }
}
