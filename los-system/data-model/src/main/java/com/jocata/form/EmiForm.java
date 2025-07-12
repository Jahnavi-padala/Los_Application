package com.jocata.form;

public class EmiForm {
    private String termMonths;
    private String principle;
    private String interest;
    private String emiAmount;
    private String totalAmount;

    public String getPrinciple() {
        return principle;
    }

    public void setPrinciple(String principle) {
        this.principle = principle;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(String emiAmount) {
        this.emiAmount = emiAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(String termMonths) {
        this.termMonths = termMonths;
    }
}
