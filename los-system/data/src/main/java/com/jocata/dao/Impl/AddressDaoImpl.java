package com.jocata.dao.Impl;

import com.jocata.dao.AddressDao;
import com.jocata.entity.Address;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AddressDaoImpl implements AddressDao {
    @Autowired
    private EntityManager entityManager;
    public Address create(Address address)
    {
        entityManager.persist(address);
        return address;
    }
    @Override
    public Address getById(Integer addressId)
    {
        return entityManager.find(Address.class, addressId);
    }
}
