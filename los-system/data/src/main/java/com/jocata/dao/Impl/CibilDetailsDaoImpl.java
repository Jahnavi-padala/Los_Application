package com.jocata.dao.Impl;

import com.jocata.dao.CibilDetailsDao;
import com.jocata.entity.LoanApplications;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CibilDetailsDaoImpl implements CibilDetailsDao {
    @PersistenceContext
    private EntityManager entityManager;

    public LoanApplications findLoanApplicationWithDetails(Integer applicationId) {
        String jpql = """
                SELECT la FROM LoanApplications la
                JOIN FETCH la.customer c
                LEFT JOIN FETCH c.cibilDetails
                LEFT JOIN FETCH c.incomeDetails
                WHERE la.applicationId = :applicationId
                """;
        TypedQuery<LoanApplications> query = entityManager.createQuery(jpql, LoanApplications.class);
        query.setParameter("applicationId", applicationId);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Transactional
    public void saveLoanApplication(LoanApplications loanApplication) {
        entityManager.merge(loanApplication);
    }
    @Override
    public List<LoanApplications> findAllApplications() {
        String jpql = """
        SELECT la FROM LoanApplications la
        JOIN FETCH la.customer c
        LEFT JOIN FETCH c.cibilDetails
        LEFT JOIN FETCH c.incomeDetails
    """;
        return entityManager.createQuery(jpql, LoanApplications.class).getResultList();
    }
}
