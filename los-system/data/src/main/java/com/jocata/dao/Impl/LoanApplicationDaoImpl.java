package com.jocata.dao.Impl;

import com.jocata.dao.LoanApplicationDao;
import com.jocata.entity.LoanApplications;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class LoanApplicationDaoImpl implements LoanApplicationDao {
    @Autowired
    private EntityManager entityManager;
    public LoanApplications create(LoanApplications loan)
    {
        entityManager.persist(loan);
        return loan;
    }
    @Override
    public LoanApplications getById(Integer applicationId) {
        return entityManager.find(LoanApplications.class, applicationId);
    }
    @Override
    public void save(LoanApplications application) {
        entityManager.merge(application);
    }
}
