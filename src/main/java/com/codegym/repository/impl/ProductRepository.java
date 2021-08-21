package com.codegym.repository.impl;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ProductRepository implements IProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Product> findAll() {

        TypedQuery<Product> query = em.createNamedQuery("findAllProducts", Product.class);
        List<Product> Products = query.getResultList();
        return Products;
    }

    @Override
    public void save(Product Product) {

    }

    @Override
    public Product findProductById(Long id) {
        TypedQuery<Product> query = em.createNamedQuery("findProductsWithId", Product.class);
        query.setParameter("id", id);
        List<Product> list = query.getResultList();

        if (list.size() > 0)
            return list.get(0);
        return null;
    }
}