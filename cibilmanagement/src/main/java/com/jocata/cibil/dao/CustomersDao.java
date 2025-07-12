package com.jocata.cibil.dao;

import com.jocata.cibil.entity.Customers;

public interface CustomersDao {
    Customers create(Customers customer);
    Customers update(Customers customer);
    Customers findByPan(String panNumber);
}
