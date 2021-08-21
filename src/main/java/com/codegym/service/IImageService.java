package com.codegym.service;

import com.codegym.model.Image;

public interface IImageService extends IGeneralService<Image> {

    void save(Image image);
}
