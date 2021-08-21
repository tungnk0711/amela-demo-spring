package com.codegym.repository;

import com.codegym.model.Product;

public interface IProductRepository extends IGeneralRepository<Product>{

    Product findProductById(Long id);
}
