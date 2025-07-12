package com.jocata.cibil.dao;

import com.jocata.cibil.entity.Accounts;
import com.jocata.cibil.entity.CreditReports;

import java.util.List;

public interface AccountDao {
    Accounts create(Accounts account);
    Accounts findByAccountNumber(String accountNumber);
    void createOrUpdate(Accounts account);
    List<Accounts> findByReportId(Integer reportId);

}
