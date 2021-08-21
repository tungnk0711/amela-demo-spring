package com.codegym.formatter;

import com.codegym.model.Province;
import com.codegym.service.impl.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ProvinceFormatter implements Formatter<Province> {

    private ProvinceService provinceService;

    @Autowired
    public ProvinceFormatter(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @Override
    public Province parse(String text, Locale locale) throws ParseException {
        return provinceService.findProvinceById(Long.parseLong(text));
    }

    @Override
    public String print(Province object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}
