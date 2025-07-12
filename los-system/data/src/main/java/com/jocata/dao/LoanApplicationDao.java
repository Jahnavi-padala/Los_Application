package com.jocata.dao;

import com.jocata.entity.LoanApplications;

public interface LoanApplicationDao {
    LoanApplications create(LoanApplications loan);
    LoanApplications getById(Integer applicationId);
    void save(LoanApplications application);
}
