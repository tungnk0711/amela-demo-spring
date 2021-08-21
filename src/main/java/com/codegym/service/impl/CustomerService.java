package com.codegym.service.impl;

import com.codegym.exception.CustomerNotFoundException;
import com.codegym.exception.DuplicateLastNameException;
import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepository;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.SQLIntegrityConstraintViolationException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {

            customerRepository.save(customer);

    }

    @Override
    public Customer findCustomerById(Long id) throws CustomerNotFoundException {
        return customerRepository.findCustomerById(id);

    }
}
