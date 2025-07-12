package com.jocata.cibil.dao.Impl;

import com.jocata.cibil.dao.CustomersDao;
import com.jocata.cibil.entity.Customers;
import com.jocata.cibil.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class CustomersDaoImpl implements CustomersDao {

    @Override
    public Customers create(Customers customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public Customers update(Customers customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public Customers findByPan(String panNumber) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Customers WHERE panNumber = :pan", Customers.class)
                    .setParameter("pan", panNumber)
                    .uniqueResult();
        }
    }
}