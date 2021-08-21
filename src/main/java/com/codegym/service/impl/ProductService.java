package com.codegym.service.impl;

import com.codegym.exception.DuplicateLastNameException;
import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product Product){

    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findProductById(id);
    }
}
