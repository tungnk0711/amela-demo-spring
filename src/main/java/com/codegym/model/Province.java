package com.codegym.model;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "findProvinceWithId",
        query = "select p from Province p where  p.id=:id")
@NamedQuery(
        name = "findAllProvinces",
        query = "select p from Province p")
@Table(name = "provinces")
public class Province {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Customer.class)
    private List<Customer> customers;

    public Province() {
    }

    public Province(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
