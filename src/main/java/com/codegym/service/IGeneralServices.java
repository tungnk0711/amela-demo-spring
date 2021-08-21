package com.codegym.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralServices <T> {
    List<T> findAll();

    T findById(Long id);

    T save(T t);

    void remove(Long id);
}

