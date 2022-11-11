package com.empresax.security.domain.service;

import java.util.List;

public interface IBaseService<T, ID> {

    T create(T entity);

    T findById(ID id);

    List<T> findAll();

    T update(T entity);

    void delete(ID id);

}
