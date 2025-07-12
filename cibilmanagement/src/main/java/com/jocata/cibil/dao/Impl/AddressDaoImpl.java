package com.jocata.cibil.dao.Impl;

import com.jocata.cibil.dao.AddressDao;
import com.jocata.cibil.entity.Accounts;
import com.jocata.cibil.entity.Addresses;
import com.jocata.cibil.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl implements AddressDao {

    @Override
    public Addresses create(Addresses addresses) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(addresses);
            transaction.commit();
            return addresses;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
    @Override
    public Addresses getByCustomerId(Integer customerId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Addresses a WHERE a.customer.customerId = :customerId";
            Query<Addresses> query = session.createQuery(hql, Addresses.class);
            query.setParameter("customerId", customerId);
            Addresses address = query.uniqueResult();
            transaction.commit();
            return address;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }



}
