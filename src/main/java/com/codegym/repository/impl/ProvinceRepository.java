package com.codegym.repository.impl;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.repository.IProvinceRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProvinceRepository implements IProvinceRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Province> findAll() {

        TypedQuery<Province> query = em.createNamedQuery("findAllProvinces", Province.class);
        List<Province> provinces = query.getResultList();
        return provinces;

    }

    @Override
    public void save(Province province) {

    }

    @Override
    public Province findProvinceById(Long id) {
        TypedQuery<Province> query = em.createNamedQuery("findProvinceWithId", Province.class);
        query.setParameter("id", id);
        List<Province> list = query.getResultList();

        return list.get(0);
    }
}
