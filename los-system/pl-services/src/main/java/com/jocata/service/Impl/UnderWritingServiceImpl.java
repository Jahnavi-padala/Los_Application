package com.jocata.service.Impl;

import com.jocata.dao.LoanApplicationDao;
import com.jocata.entity.LoanApplications;
import com.jocata.service.CreditAsseService;
import com.jocata.service.UnderWritingService;
import com.jocata.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UnderWritingServiceImpl implements UnderWritingService {
    @Autowired
    private LoanApplicationDao loanApplicationDao;

    @Autowired
    private CreditAsseService creditAssessmentService; // Injected here

    @Override
    public String evaluate(String applicationIdStr, String loanAmountStr) {
        int applicationId = Integer.parseInt(applicationIdStr);
        BigDecimal requested = new BigDecimal(loanAmountStr);

        LoanApplications app = loanApplicationDao.getById(applicationId);
        if (app == null) {
            throw new RuntimeException("Loan Application not found");
        }


        BigDecimal eligible = creditAssessmentService.evaluateLoanEligibility(applicationIdStr);

        BigDecimal existingRequest = app.getLoanAmount();


        if (requested.compareTo(existingRequest) == 0) {
            if (requested.compareTo(eligible) <= 0) {
                app.setStatus(Status.APPROVED);
                app.setApprovedAmount(requested);
                loanApplicationDao.save(app);// Optionally update
                return "Approved (your original request of ₹"
                        + requested + " is within your eligibility of ₹"
                        + eligible + ")";
            } else {
                app.setStatus(Status.REJECTED);
                loanApplicationDao.save(app);
                return "Rejected (your original request of ₹"
                        + requested + " exceeds eligible ₹"
                        + eligible + ")";
            }
        }

        if (requested.compareTo(eligible) <= 0) {
            app.setApprovedAmount(requested);
            app.setStatus(Status.APPROVED);
            loanApplicationDao.save(app);
            return "Approved for your new request of ₹" + requested;
        } else {
            app.setStatus(Status.REJECTED);
            loanApplicationDao.save(app);
            return "Rejected: You requested more than you are eligible for (₹"
                    + eligible + ")";
        }
    }
}
