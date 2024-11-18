package com.cookease.cook_ease.application.service.impl;

import com.cookease.cook_ease.domain.exception.ModelNotFountException;
import com.cookease.cook_ease.infraestructure.repository.IGenericRepo;
import com.cookease.cook_ease.application.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T,ID> implements ICRUD<T,ID> {

    abstract IGenericRepo<T,ID> getRepo();

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow(()-> new ModelNotFountException("ID NO ENCONTRADO"));
    }

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public void deleteById(ID id) throws Exception {
        getRepo().deleteById(id);
    }
}
