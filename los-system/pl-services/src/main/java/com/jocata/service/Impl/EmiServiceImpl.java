package com.jocata.service.Impl;

import com.jocata.dao.CibilDetailsDao;
import com.jocata.entity.LoanApplications;
import com.jocata.form.EmiForm;
import com.jocata.service.CreditAsseService;
import com.jocata.service.EmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
@Service
public class EmiServiceImpl implements EmiService {
    @Autowired
    private CibilDetailsDao creditAssessmentDao;

    @Autowired
    private CreditAsseService creditAssessmentService;

    @Override
    public List<EmiForm> calculateEmisByApplicationId(String applicationId) {
        LoanApplications app = creditAssessmentDao.findLoanApplicationWithDetails(Integer.parseInt(applicationId));
        if (app == null) {
            return new ArrayList<>();
        }

        List<EmiForm> emiForms = new ArrayList<>();

        BigDecimal principal = app.getLoanAmount();
        int loanTenure = app.getTermMonths();
        BigDecimal annualInterestRate = creditAssessmentService.getInterestRateByApplicationId(applicationId);
        BigDecimal monthlyRate = annualInterestRate.divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_UP);

        // Calculate constant EMI using standard formula
        BigDecimal onePlusRPowerN = BigDecimal.ONE.add(monthlyRate).pow(loanTenure);
        BigDecimal emi = principal.multiply(monthlyRate).multiply(onePlusRPowerN)
                .divide(onePlusRPowerN.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);

        BigDecimal balance = principal;

        for (int month = 1; month <= loanTenure; month++) {
            BigDecimal interest = balance.multiply(monthlyRate).setScale(2, RoundingMode.HALF_UP);
            BigDecimal principalPart = emi.subtract(interest).setScale(2, RoundingMode.HALF_UP);
            balance = balance.subtract(principalPart).setScale(2, RoundingMode.HALF_UP);

            EmiForm emiForm = new EmiForm();
            emiForm.setTermMonths(String.valueOf(month));
            emiForm.setInterest(interest.toPlainString());
            emiForm.setPrinciple(principalPart.toPlainString());
            emiForm.setEmiAmount(emi.toPlainString());
            emiForm.setTotalAmount(balance.max(BigDecimal.ZERO).toPlainString()); // Remaining balance

            emiForms.add(emiForm);
        }

        return emiForms;
    }
}
