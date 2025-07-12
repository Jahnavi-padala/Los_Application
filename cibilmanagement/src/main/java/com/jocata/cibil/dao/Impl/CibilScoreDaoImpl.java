package com.jocata.cibil.dao.Impl;


import com.jocata.cibil.dao.CibilScoreDao;
import com.jocata.cibil.entity.CibilScores;
import com.jocata.cibil.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.hibernate.Transaction;
@Repository
public class CibilScoreDaoImpl implements CibilScoreDao {

    @Override
    public CibilScores create(CibilScores score) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(score);

            session.getTransaction().commit();
            return score;
        }
    }

    @Override
    public CibilScores findByReportId(Integer reportId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM CibilScores WHERE creditReport.reportId = :reportId", CibilScores.class)
                    .setParameter("reportId", reportId)
                    .uniqueResult();
 }
    }


}
