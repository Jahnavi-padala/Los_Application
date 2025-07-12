package com.jocata.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="pan_details")
public class PanDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pan_id")
    private Integer panId;
    @Column(name="pan_number")

    private String panNumber;
    @Column(name="full_name")
    private String fullName;
    @Column(name="dob")
    private Date dob;
    @Column(name="father_name")
    private String fatherName;
    @Column(name="status")
    private String status;
    @Column(name="issued_on")
    private Date issuedOn;
    @Column(name="gender")
    private String gender;
    @Column(name="category")
    private String category;
    @Column(name="address")
    private String address;
    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;



    public Integer getPanId() {
        return panId;
    }

    public void setPanId(Integer panId) {
        this.panId = panId;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(Date issuedOn) {
        this.issuedOn = issuedOn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }
}
