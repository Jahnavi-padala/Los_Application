package com.jocata.dao;

import com.jocata.entity.LoanApplications;

import java.util.List;

public interface CibilDetailsDao {
    LoanApplications findLoanApplicationWithDetails(Integer applicationId);
    void saveLoanApplication(LoanApplications loanApplication);
    List<LoanApplications> findAllApplications();

}
