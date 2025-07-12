package com.jocata.service.Impl;

import com.jocata.dao.CibilDetailsDao;
import com.jocata.entity.CibilDetails;
import com.jocata.entity.Customers;
import com.jocata.entity.IncomeDetails;
import com.jocata.entity.LoanApplications;
import com.jocata.service.CreditAsseService;
import com.jocata.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CreditAsseServiceImpl implements CreditAsseService {
    @Autowired
    private CibilDetailsDao creditAssessmentDao;

    @Override
    public String evaluateLoan(String loanApplicationIdStr) {
        int loanApplicationId = Integer.parseInt(loanApplicationIdStr);
        LoanApplications loanApplication = creditAssessmentDao.findLoanApplicationWithDetails(loanApplicationId);
        if (loanApplication == null) {
            throw new RuntimeException("Loan Application not found");
        }

        Customers customer = loanApplication.getCustomer();
        CibilDetails cibil = customer.getCibilDetails();
        IncomeDetails incomeData = customer.getIncomeDetails();

        if (customer == null || cibil == null || incomeData == null) {
            throw new RuntimeException("Incomplete customer information.");
        }

        String status = cibil.getCreditStatus();
        if ("Poor".equalsIgnoreCase(status)) {
            loanApplication.setStatus(Status.REJECTED);
            creditAssessmentDao.saveLoanApplication(loanApplication);
            return "Loan Rejected: Poor credit status.";
        }

        if (cibil.getNoOfActiveAccounts() > 5) {
            loanApplication.setStatus(Status.REJECTED);
            creditAssessmentDao.saveLoanApplication(loanApplication);
            return "Loan Rejected: More than 5 active loans.";
        }

        if (cibil.getNoOfEnquiries() > 5) {
            loanApplication.setStatus(Status.REJECTED);
            creditAssessmentDao.saveLoanApplication(loanApplication);
            return "Loan Rejected: Too many loan enquiries.";
        }

        BigDecimal interestRate = getInterestRateByApplicationId(loanApplicationIdStr);
        BigDecimal monthlyIncome = incomeData.getMonthlyIncome();
        BigDecimal maxEmiCapacity = monthlyIncome.multiply(BigDecimal.valueOf(0.75));
        BigDecimal existingEmis = cibil.getCurrentEmi();
        BigDecimal availForNew = maxEmiCapacity.subtract(existingEmis);

        if (availForNew.compareTo(existingEmis) <= 0) {
            loanApplication.setStatus(Status.REJECTED);
            creditAssessmentDao.saveLoanApplication(loanApplication);
            return "Loan Rejected: Existing EMIs exceed income eligibility.";
        }

        return evaluateEligibilityAndDecision(loanApplication, interestRate, availForNew);
    }

    @Override
    public BigDecimal evaluateLoanEligibility(String applicationIdStr) {
        int id = Integer.parseInt(applicationIdStr);
        LoanApplications app = creditAssessmentDao.findLoanApplicationWithDetails(id);
        if (app == null) throw new RuntimeException("Application not found");

        BigDecimal rate = getInterestRateByApplicationId(applicationIdStr);
        BigDecimal income = app.getCustomer().getIncomeDetails().getMonthlyIncome();
        BigDecimal maxEmi = income.multiply(BigDecimal.valueOf(0.75));
        BigDecimal avail = maxEmi.subtract(app.getCustomer().getCibilDetails().getCurrentEmi());

        BigDecimal eligible = calculateEligibleAmount(applicationIdStr);

        return eligible;
    }

    @Override
    public BigDecimal calculateEligibleAmount(String applicationIdStr) {
        int id = Integer.parseInt(applicationIdStr);
        LoanApplications app = creditAssessmentDao.findLoanApplicationWithDetails(id);
        BigDecimal rate = getInterestRateByApplicationId(applicationIdStr);
        BigDecimal income = app.getCustomer().getIncomeDetails().getMonthlyIncome();
        BigDecimal maxEmi = income.multiply(BigDecimal.valueOf(0.75));
        BigDecimal avail = maxEmi.subtract(app.getCustomer().getCibilDetails().getCurrentEmi());

        return calculatePrincipal(avail, rate, app.getTermMonths());
    }

    @Override
    public BigDecimal calculatePrincipal(BigDecimal emi, BigDecimal annualRate, int tenureMonths) {
        if (tenureMonths <= 0) throw new RuntimeException("Invalid tenure");
        BigDecimal r = annualRate.divide(BigDecimal.valueOf(1200), 10, RoundingMode.HALF_UP);
        BigDecimal factor = BigDecimal.ONE.add(r).pow(tenureMonths);
        BigDecimal numerator = factor.subtract(BigDecimal.ONE);
        BigDecimal denominator = r.multiply(factor);
        BigDecimal multiplier = numerator.divide(denominator, 2, RoundingMode.HALF_UP);
        return emi.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getInterestRateByApplicationId(String applicationIdStr) {
        int id = Integer.parseInt(applicationIdStr);
        LoanApplications app = creditAssessmentDao.findLoanApplicationWithDetails(id);
        String status = app.getCustomer().getCibilDetails().getCreditStatus();
        return switch (status) {
            case "Good" -> BigDecimal.valueOf(10.5);
            case "Average" -> BigDecimal.valueOf(10.75);
            default -> throw new RuntimeException("Unknown credit status: " + status);
        };
    }

    public String evaluateEligibilityAndDecision(LoanApplications loanApplication, BigDecimal interestRate, BigDecimal availForNew) {
        int termMonths = loanApplication.getTermMonths();
        BigDecimal maxLoan = calculatePrincipal(availForNew, interestRate, termMonths);
        BigDecimal requested = loanApplication.getLoanAmount();

        if (requested == null) {
            throw new RuntimeException("Requested loan amount is missing.");
        }

        if (requested.compareTo(maxLoan) > 0) {
            return "You’re eligible for up to: ₹" + maxLoan;
        }

        creditAssessmentDao.saveLoanApplication(loanApplication);
        return String.format(
                "You asked for: ₹%s, Eligible amount: ₹%s at %s%% interest.",
                requested.setScale(2, RoundingMode.HALF_UP),
                maxLoan.setScale(2, RoundingMode.HALF_UP),
                interestRate
        );
    }
}
