package com.jocata.aadhaar.details.form;

public class Address {
    private String street;
    private String city;
    private String state;
    private String pinCode;
    public void setCity(String city) {
        this.city = city;
    }
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;

    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getStreet() {
        return street;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getPinCode() {
        return pinCode;
    }
}
