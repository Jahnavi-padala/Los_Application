package com.jocata.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers")

public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer CustomerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "dob")
    private Date dob;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "aadhaar_number")
    private String aadhaarNumber;
    @Column(name = "pan_number")
    private String panNumber;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<LoanApplications> loanApplication;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Address address;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private IncomeDetails incomeDetails;
    @OneToOne(mappedBy ="customer",cascade=CascadeType.ALL)
    private PanDetails panDetails;
    @OneToOne(mappedBy ="customer",cascade=CascadeType.ALL)
    private AadhaarDetails aadhaarDetails;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private CibilDetails cibilDetails;



    public Integer getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Integer customerId) {
        CustomerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<LoanApplications> getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(List<LoanApplications> loanApplication) {
        this.loanApplication = loanApplication;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public IncomeDetails getIncomeDetails() {
        return incomeDetails;
    }

    public void setIncomeDetails(IncomeDetails incomeDetails) {
        this.incomeDetails = incomeDetails;
    }

    public PanDetails getPanDetails() {
        return panDetails;
    }

    public void setPanDetails(PanDetails panDetails) {
        this.panDetails = panDetails;
    }

    public CibilDetails getCibilDetails() {
        return cibilDetails;
    }

    public void setCibilDetails(CibilDetails cibilDetails) {
        this.cibilDetails = cibilDetails;
    }

    public AadhaarDetails getAadhaarDetails() {
        return aadhaarDetails;
    }

    public void setAadhaarDetails(AadhaarDetails aadhaarDetails) {
        this.aadhaarDetails = aadhaarDetails;
    }
}
