package com.jocata.panservice.panservice.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PanResponse {
   private PanDetails panDetails;
    private String  status;
    private String message;
    private Timestamp timeStamp;

    public PanResponse(PanDetails panDetails, String status, String message) {
        this.panDetails = panDetails;
        this.status = status;
        this.message = message;
        this.timeStamp = Timestamp.valueOf(LocalDateTime.now());
}
  public void setMessage(String message) {
        this.message = message;
  }
  public String getMessage() {
        return message;
  }
  public void setStatus(String status) {
            this.status = status;
  }

    public PanDetails getPanDetails() {
            return panDetails;
    }
    public void setPanDetails(PanDetails panDetails) {
        this.panDetails = panDetails;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
