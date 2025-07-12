package com.jocata.dao;

import com.jocata.entity.Customers;

public interface CustomerDao {
    Customers create(Customers customer);

    Customers getById(Integer customerId);

    Customers findByPan(String panNumber);

    Customers findByAadhaar(String aadhaarNumber);


}
