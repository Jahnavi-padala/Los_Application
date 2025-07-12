package com.jocata.aadhaar.details.form;

import java.sql.Timestamp;

public class AadhaarResponse {
    private AadhaarDetails aadhaarDetails;
    private String  status;
    private String message;
    private Timestamp timeStamp;
    public AadhaarResponse(AadhaarDetails aadhaarDetails, String status, String message) {
        this.aadhaarDetails = aadhaarDetails;
        this.status = status;
        this.message = message;
        this.timeStamp = new Timestamp(System.currentTimeMillis());
    }
    public AadhaarDetails getAadhaarDetails() {
        return aadhaarDetails;
    }
    public void setAadhaarDetails(AadhaarDetails aadhaarDetails) {
        this.aadhaarDetails = aadhaarDetails;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Timestamp getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
