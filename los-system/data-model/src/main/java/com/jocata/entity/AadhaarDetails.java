package com.jocata.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="aadhaar_details")
public class AadhaarDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="aadhaar_id")
    private Integer aadhaarId;
    @Column(name="aadhaar_number")
    private String aadhaarNumber;
    @Column(name="name")
    private String name;
    @Column(name="gender")
    private String gender;
    @Column(name="dob")
    private Date dob;
    @Column(name="father_name")
    private String fatherName;
    @Column(name="city")
    private String city;
    @Column(name="street")
    private String street;
    @Column(name="state")
    private String state;
    @Column(name="pincode")
    private String pincode;
    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;

    public Integer getAadhaarId() {
        return aadhaarId;
    }

    public void setAadhaarId(Integer aadhaarId) {
        this.aadhaarId = aadhaarId;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }
}
