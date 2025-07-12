package com.jocata.form;


public class IncomeDetailsForm {
    private String incomeId;
    private String monthlyIncome;
    private String employmentStatus;
    private String employerName;
    private String yearsAtJob;
    private String customerId;

    public String getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(String incomeId) {
        this.incomeId = incomeId;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }


    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getYearsAtJob() {
        return yearsAtJob;
    }

    public void setYearsAtJob(String yearsAtJob) {
        this.yearsAtJob = yearsAtJob;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }
}
