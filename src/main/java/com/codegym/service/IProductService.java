package com.codegym.service;

import com.codegym.model.Product;

public interface IProductService extends IGeneralService<Product>{
    void save(Product product);

    Product findProductById(Long id);
}
