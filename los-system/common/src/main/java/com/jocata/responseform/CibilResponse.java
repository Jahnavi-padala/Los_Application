package com.jocata.responseform;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

public class CibilResponse {
    private String cibilId;

    private String cibilScore;

    private String lastUpdated;

    private String creditStatus;

    private String noOfEnquiries;

    private String noOfActiveAccounts;

    private String loanOutstanding;

    private String currentEmi;

    private  String createdTimestamp;

    private String customerId;

    public String getCibilId() {
        return cibilId;
    }

    public void setCibilId(String cibilId) {
        this.cibilId = cibilId;
    }

    public String getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(String cibilScore) {
        this.cibilScore = cibilScore;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public String getNoOfEnquiries() {
        return noOfEnquiries;
    }

    public void setNoOfEnquiries(String noOfEnquiries) {
        this.noOfEnquiries = noOfEnquiries;
    }

    public String getNoOfActiveAccounts() {
        return noOfActiveAccounts;
    }

    public void setNoOfActiveAccounts(String noOfActiveAccounts) {
        this.noOfActiveAccounts = noOfActiveAccounts;
    }

    public String getLoanOutstanding() {
        return loanOutstanding;
    }

    public void setLoanOutstanding(String loanOutstanding) {
        this.loanOutstanding = loanOutstanding;
    }



    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCurrentEmi() {
        return currentEmi;
    }

    public void setCurrentEmi(String currentEmi) {
        this.currentEmi = currentEmi;
    }
}
