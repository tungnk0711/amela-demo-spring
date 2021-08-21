package com.codegym.repository.impl;

import com.codegym.exception.CustomerNotFoundException;
import com.codegym.model.Customer;
import com.codegym.model.Product;
import com.codegym.repository.ICustomerRepository;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Transactional
public class CustomerRepository implements ICustomerRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> findAll() {

        //TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
        TypedQuery<Customer> query = em.createNamedQuery("findAllCustomers", Customer.class);
        List<Customer> customers = query.getResultList();
        return customers;

    }

    // demo store procedure
    @Override
    public void save(Customer customer) {

        Integer provinceId = Integer.valueOf(customer.getProvince().getId().intValue());

        StoredProcedureQuery spAddCustomer = em.createNamedStoredProcedureQuery("addCustomer");
        spAddCustomer.setParameter("firstName", customer.getFirstName());
        spAddCustomer.setParameter("lastName", customer.getLastName());
        spAddCustomer.setParameter("province_id", provinceId);
        spAddCustomer.setParameter("avatar", customer.getAvatar());
        spAddCustomer.execute();
    }

    @Override
    public Customer findCustomerById(Long id) throws CustomerNotFoundException {
        TypedQuery<Customer> query = em.createNamedQuery("findCustomerWithId", Customer.class);
        query.setParameter("id", id);
        List<Customer> list = query.getResultList();

            return list.get(0);

    }

    /*@Override
    public void save(Customer customer) {
        if (customer.getId() != null) {
            em.merge(customer);
        } else {
            em.persist(customer);
        }
    }*/
}
