package com.codegym.repository;

import com.codegym.exception.CustomerNotFoundException;
import com.codegym.model.Customer;

public interface ICustomerRepository extends IGeneralRepository<Customer> {

    Customer findCustomerById(Long id) throws CustomerNotFoundException;
}
