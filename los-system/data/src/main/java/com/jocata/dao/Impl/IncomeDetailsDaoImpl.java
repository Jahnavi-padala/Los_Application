package com.jocata.dao.Impl;

import com.jocata.dao.IncomeDetailsDao;
import com.jocata.entity.IncomeDetails;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class IncomeDetailsDaoImpl implements IncomeDetailsDao {
    @Autowired
    private EntityManager entityManager;
    public IncomeDetails create(IncomeDetails income)
    {
        entityManager.persist(income);
        return income;
    }
    @Override
    public IncomeDetails getById(Integer incomeId) {
        return entityManager.find(IncomeDetails.class, incomeId);
    }

}
