package com.jocata.cibil.form;

public class CibilScoreResponseForm {

    private String scoreId;
    private String score;
    private String scoreDate;
    private String riskGrade;


    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(String scoreDate) {
        this.scoreDate = scoreDate;
    }

    public String getRiskGrade() {
        return riskGrade;
    }

    public void setRiskGrade(String riskGrade) {
        this.riskGrade = riskGrade;
    }
}
