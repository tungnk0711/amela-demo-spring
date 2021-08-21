package com.codegym.model;

import javax.persistence.*;

@Entity
@NamedQuery(name = "findCustomerWithId",
        query = "select c from Customer c where  c.id=:id")
@NamedQuery(
        name = "findAllCustomers",
        query = "select c from Customer c")
@NamedStoredProcedureQuery(
        name = "addCustomer",
        procedureName = "spAddCustomer",
        parameters = {
                @StoredProcedureParameter(name = "firstName", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "lastName", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "province_id", mode = ParameterMode.IN, type = Integer.class),
                @StoredProcedureParameter(name = "avatar", mode = ParameterMode.IN, type = String.class)
        }
)
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;

    @Column(unique = true)
    private String lastName;

    private String avatar;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(Long id ,String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(String firstName, String lastName, Province province, String avatar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.province = province;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
