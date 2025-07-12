package com.jocata.cibil.dao.Impl;


import com.jocata.cibil.dao.EnquiriesDao;
import com.jocata.cibil.entity.Enquiries;
import com.jocata.cibil.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.hibernate.Transaction;

import java.util.List;

@Repository
public class EnquriesDaoImpl  implements EnquiriesDao {
    @Override
    public Enquiries create(Enquiries enquiries) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(enquiries);
            transaction.commit();
            return enquiries;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
    @Override
    public List<Enquiries> findByReportId(Integer reportId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Enquiries e WHERE e.creditReport.reportId = :reportId";
            Query<Enquiries> query = session.createQuery(hql, Enquiries.class);
            query.setParameter("reportId", reportId);
            return query.list();
        }
    }
}
