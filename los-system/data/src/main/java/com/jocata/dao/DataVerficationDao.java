package com.jocata.dao;

import com.jocata.entity.LoanApplications;

public interface DataVerficationDao {
    LoanApplications save(LoanApplications loanApplication);
    LoanApplications findById(Integer applicationId);
}
