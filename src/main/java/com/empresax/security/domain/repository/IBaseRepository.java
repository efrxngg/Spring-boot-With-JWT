package com.empresax.security.domain.repository;

import java.util.List;
import java.util.Optional;

import com.empresax.security.domain.User;

public interface IBaseRepository<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    T update(T entity);

    List<User> findAll();

    void delete(ID id);

}
