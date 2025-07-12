package com.jocata.cibil.entity;

import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "Customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Integer customerId;

    @Column(name="full_name")
    private String fullName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;
   @Column(name = "gender")
    private String gender;

    @Column(name = "pan_number")
    private String panNumber;
     @Column(name="mobile")
    private String mobile;
     @Column(name="email")

    private String email;
     @Column(name="aadhaar")

    private String aadhaar;

    @OneToOne
    @JoinColumn(name = "report_id")
    private CreditReports creditReport;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Addresses address;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }



    public Addresses getAddress() {
        return address;
    }

    public void setAddress(Addresses address) {
        this.address = address;
    }

    public CreditReports getCreditReport() {
        return creditReport;
    }

    public void setCreditReport(CreditReports creditReport) {
        this.creditReport = creditReport;
    }
}
