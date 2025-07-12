package com.jocata.cibil.dao.Impl;

import com.jocata.cibil.dao.AccountDao;
import com.jocata.cibil.entity.Accounts;
import com.jocata.cibil.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jocata.cibil.util.HibernateUtil.sessionFactory;

@Repository
public class AcountsDaoImpl implements AccountDao {
    @Override
    public Accounts create(Accounts account) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(account);
            session.getTransaction().commit();
            return account;
        }
    }

    @Override
    public Accounts findByAccountNumber(String accountNumber) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Accounts WHERE accountNumber = :accNum", Accounts.class)
                    .setParameter("accNum", accountNumber)
                    .uniqueResult();
        }
    }

    @Override
    public void createOrUpdate(Accounts account) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(account);
            session.getTransaction().commit();
}
}



    @Override
    public List<Accounts> findByReportId(Integer reportId) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            List<Accounts> accounts = session.createQuery(
                            "FROM Accounts WHERE creditReport.reportId = :reportId", Accounts.class)
                    .setParameter("reportId", reportId)
                    .getResultList();

            tx.commit();

            System.out.println("Accounts fetched: " + accounts.size());
            return accounts;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
            return List.of();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }




}
