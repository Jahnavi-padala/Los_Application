package com.jocata.cibil.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "creditReports")
public class CreditReports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="report_id")
    private Integer reportId;

    @Column(name = "generated_on")
    private LocalDateTime generatedOn;

    @OneToOne(mappedBy = "creditReport", cascade = CascadeType.ALL)
    private Customers customer;

    @OneToOne(mappedBy = "creditReport", cascade = CascadeType.ALL)
    private CibilScores cibilScore;

    @OneToMany(mappedBy = "creditReport", cascade = CascadeType.ALL)
    private List<Accounts> accounts;

    @OneToMany(mappedBy = "creditReport", cascade = CascadeType.ALL)
    private List<Enquiries> enquiries;

    @OneToMany(mappedBy = "creditReport", cascade = CascadeType.ALL)
    private List<Remarks> remarks;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public LocalDateTime getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(LocalDateTime generatedOn) {
        this.generatedOn = generatedOn;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public CibilScores getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(CibilScores cibilScore) {
        this.cibilScore = cibilScore;
    }

    public List<Accounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Accounts> accounts) {
        this.accounts = accounts;
    }

    public List<Enquiries> getEnquiries() {
        return enquiries;
    }

    public void setEnquiries(List<Enquiries> enquiries) {
        this.enquiries = enquiries;
    }

    public List<Remarks> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<Remarks> remarks) {
        this.remarks = remarks;
    }
}
