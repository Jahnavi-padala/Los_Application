package com.jocata.cibil.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "Remarks")
public class Remarks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="remark_id")
    private Integer remarkId;
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private CreditReports creditReport;


    public Integer getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(Integer remarkId) {
        this.remarkId = remarkId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public CreditReports getCreditReport() {
        return creditReport;
    }

    public void setCreditReport(CreditReports creditReport) {
        this.creditReport = creditReport;
    }
}
