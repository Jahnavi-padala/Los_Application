package com.jocata.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
@Entity
@Table(name="cibil_details")
public class CibilDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name=" cibil_id")
    private Integer cibilId;
    @Column(name="cibil_score")
    private Integer cibilScore;
    @Column(name=" last_updated")
    private Date lastUpdated;
    @Column(name="credit_status")
    private String creditStatus;
    @Column(name="no_of_enquiries")
    private Integer noOfEnquiries;
    @Column(name="no_of_active_accounts")
    private Integer noOfActiveAccounts;
    @Column(name="loan_outstanding")
    private BigDecimal loanOutstanding;
    @Column(name="current_emi")
    private BigDecimal currentEmi;
    @Column(name="created_timestamp")
    private LocalDateTime createdTimestamp;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;


    public Integer getCibilId() {
        return cibilId;
    }

    public void setCibilId(Integer cibilId) {
        this.cibilId = cibilId;
    }

    public Integer getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(Integer cibilScore) {
        this.cibilScore = cibilScore;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Integer getNoOfEnquiries() {
        return noOfEnquiries;
    }

    public void setNoOfEnquiries(Integer noOfEnquiries) {
        this.noOfEnquiries = noOfEnquiries;
    }

    public Integer getNoOfActiveAccounts() {
        return noOfActiveAccounts;
    }

    public void setNoOfActiveAccounts(Integer noOfActiveAccounts) {
        this.noOfActiveAccounts = noOfActiveAccounts;
    }

    public BigDecimal getLoanOutstanding() {
        return loanOutstanding;
    }

    public void setLoanOutstanding(BigDecimal loanOutstanding) {
        this.loanOutstanding = loanOutstanding;
    }



    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public BigDecimal getCurrentEmi() {
        return currentEmi;
    }

    public void setCurrentEmi(BigDecimal currentEmi) {
        this.currentEmi = currentEmi;
    }
}
