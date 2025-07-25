package com.jocata.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "income_details")
public class IncomeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_id")

    private Integer incomeId;

    @Column(name = "monthly_income")
    private BigDecimal monthlyIncome;
    @Column(name = "employment_status")
    private String employmentStatus;
    @Column(name = "employer_name")
    private String employerName;
    @Column(name = "years_at_job")
    private Integer yearsAtJob;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public Integer getYearsAtJob() {
        return yearsAtJob;
    }

    public void setYearsAtJob(Integer yearsAtJob) {
        this.yearsAtJob = yearsAtJob;
    }
}
