package com.jocata.dao;

import com.jocata.entity.IncomeDetails;

public interface IncomeDetailsDao {
    IncomeDetails create(IncomeDetails income);
    IncomeDetails getById(Integer incomeId);}
