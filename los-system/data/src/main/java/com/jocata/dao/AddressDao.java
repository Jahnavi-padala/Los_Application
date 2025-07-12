package com.jocata.dao;

import com.jocata.entity.Address;

public interface AddressDao {
    Address create(Address address);
    Address getById(Integer addressId);
}
