package com.codegym.service;

import com.codegym.exception.CustomerNotFoundException;
import com.codegym.exception.DuplicateLastNameException;
import com.codegym.model.Customer;

public interface ICustomerService extends IGeneralService<Customer> {
    void save(Customer customer);

    Customer findCustomerById(Long id) throws CustomerNotFoundException;
}
