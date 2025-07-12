package com.jocata.cibil.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Addresses")
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Integer addressId;

    @Column(name="hs_no")
    private String hsNo;

    @Column(name="street")
    private String street;

    @Column(name="city")
    private String city;

    @Column(name="pincode")
    private String pincode;
    @Column(name="state")
    private String state;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;


    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getHsNo() {
        return hsNo;
    }

    public void setHsNo(String hsNo) {
        this.hsNo = hsNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
