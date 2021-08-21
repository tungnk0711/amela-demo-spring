package com.codegym.service;

import com.codegym.exception.DuplicateLastNameException;

import java.util.List;

public interface IGeneralService <T> {
    List<T> findAll();

}
