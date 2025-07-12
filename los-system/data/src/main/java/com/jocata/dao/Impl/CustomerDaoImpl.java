package com.jocata.dao.Impl;

import com.jocata.dao.CustomerDao;
import com.jocata.entity.Customers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {
        @Autowired
        private EntityManager entityManager;
    @Override
    public Customers create(Customers customer) {
        entityManager.persist(customer);
         return customer;
    }
    @Override
    public Customers getById(Integer customerId) {
        return entityManager.find(Customers.class, customerId);
    }

    @Override
    public Customers findByPan(String panNumber) {
        try {
            return entityManager.createQuery(
                            "SELECT c FROM Customers c WHERE c.panNumber = :pan", Customers.class)
                    .setParameter("pan", panNumber)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Customers findByAadhaar(String aadhaarNumber) {
        try {
            return entityManager.createQuery(
                            "SELECT c FROM Customers c WHERE c.aadhaarNumber = :aadhaar", Customers.class)
                    .setParameter("aadhaar", aadhaarNumber)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }



}
