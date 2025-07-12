package com.jocata.cibil.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Enquiries")
public class Enquiries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="enquiry_Id")
    private Integer enquiryId;
    @Column(name="enquiry_date")

    private Date enquiryDate;
    @Column(name="member_name")
    private String memberName;
    @Column(name="enquiry_purpose")
    private String enquiryPurpose;
    @Column(name="enquiry_amount")
    private BigDecimal enquiryAmount;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private CreditReports creditReport;

    public Date getEnquiryDate() {
        return enquiryDate;
    }

    public void setEnquiryDate(Date enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    public Integer getEnquiryId() {
        return enquiryId;
    }

    public void setEnquiryId(Integer enquiryId) {
        this.enquiryId = enquiryId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getEnquiryPurpose() {
        return enquiryPurpose;
    }

    public void setEnquiryPurpose(String enquiryPurpose) {
        this.enquiryPurpose = enquiryPurpose;
    }

    public BigDecimal getEnquiryAmount() {
        return enquiryAmount;
    }

    public void setEnquiryAmount(BigDecimal enquiryAmount) {
        this.enquiryAmount = enquiryAmount;
    }


    public CreditReports getCreditReport() {
        return creditReport;
    }

    public void setCreditReport(CreditReports creditReport) {
        this.creditReport = creditReport;
    }
}
