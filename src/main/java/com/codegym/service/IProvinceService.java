package com.codegym.service;

import com.codegym.exception.CustomerNotFoundException;
import com.codegym.model.Customer;
import com.codegym.model.Province;

public interface IProvinceService extends IGeneralServices<Province>{

    Province findProvinceById(Long id);

}
