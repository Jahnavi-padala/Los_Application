package com.jocata.cibil.dao.Impl;

import com.jocata.cibil.dao.RemarksDao;
import com.jocata.cibil.entity.Remarks;
import com.jocata.cibil.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RemarksDaoImpl implements RemarksDao {
    @Override
    public Remarks create(Remarks remarks) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.persist(remarks);
            transaction.commit();
            return remarks;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<Remarks> findByReportId(Integer reportId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Remarks r WHERE r.creditReport.reportId = :reportId";
            Query<Remarks> query = session.createQuery(hql, Remarks.class);
            query.setParameter("reportId", reportId);
            List<Remarks> remarksList = query.list();
            transaction.commit();
            return remarksList;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}
