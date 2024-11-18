package com.cookease.cook_ease.application.service;

import java.util.List;

public interface ICRUD<T,ID> {

    List<T> findAll() throws Exception;

    T findById(ID id) throws Exception;

    T save(T t) throws Exception;

    T update(T t) throws Exception;

    void deleteById(ID id) throws Exception;

}
