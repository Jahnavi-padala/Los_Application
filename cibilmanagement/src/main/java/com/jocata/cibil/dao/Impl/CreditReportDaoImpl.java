package com.jocata.cibil.dao.Impl;

import com.jocata.cibil.dao.CreditReportDao;
import com.jocata.cibil.entity.CibilScores;
import com.jocata.cibil.entity.CreditReports;
import com.jocata.cibil.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.hibernate.Transaction;
@Repository
public class CreditReportDaoImpl implements CreditReportDao {
    @Override
    public CreditReports create(CreditReports creditReports) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(creditReports);
            transaction.commit();
            return creditReports;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

}
