package com.jocata.cibil.dao;

import com.jocata.cibil.entity.Addresses;

public interface AddressDao {
    Addresses create(Addresses addresses);
    Addresses getByCustomerId(Integer customerId);

}
