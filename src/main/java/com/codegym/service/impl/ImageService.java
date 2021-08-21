package com.codegym.service.impl;

import com.codegym.model.Customer;
import com.codegym.model.Image;
import com.codegym.repository.ICustomerRepository;
import com.codegym.repository.IImageRepository;
import com.codegym.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ImageService implements IImageService {
    @Autowired
    private IImageRepository imageRepository;

    @Override
    public List<Image> findAll() {

        return imageRepository.findAll();
    }

    @Override
    public void save(Image image) {
        imageRepository.save(image);

    }
}
