package com.jocata.cibil.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "CibilScores")
public class CibilScores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="score_id")
    private Integer scoreId;
    @Column(name="score")

    private Integer score;

    @Column(name = "score_date")
    private Date scoreDate;

    @Column(name = "risk_grade")
    private String riskGrade;

    @OneToOne
    @JoinColumn(name = "report_id")
    private CreditReports creditReport;

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(Date scoreDate) {
        this.scoreDate = scoreDate;
    }

    public String getRiskGrade() {
        return riskGrade;
    }

    public void setRiskGrade(String riskGrade) {
        this.riskGrade = riskGrade;
    }

    public CreditReports getCreditReport() {
        return creditReport;
    }

    public void setCreditReport(CreditReports creditReport) {
        this.creditReport = creditReport;
    }
}
