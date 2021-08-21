package com.codegym.service.impl;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.repository.ICustomerRepository;
import com.codegym.repository.IProvinceRepository;
import com.codegym.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProvinceService implements IProvinceService {
    @Autowired
    private IProvinceRepository provinceRepository;

    @Override
    public List<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Province findById(Long id) {
        return null;
    }

    @Override
    public Province save(Province province) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Province findProvinceById(Long id) {
        return provinceRepository.findProvinceById(id);
    }
}
