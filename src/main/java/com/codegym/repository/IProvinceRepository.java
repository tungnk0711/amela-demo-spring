package com.codegym.repository;

import com.codegym.exception.CustomerNotFoundException;
import com.codegym.model.Customer;
import com.codegym.model.Province;

public interface IProvinceRepository extends IGeneralRepository<Province>{

    Province findProvinceById(Long id);
}
