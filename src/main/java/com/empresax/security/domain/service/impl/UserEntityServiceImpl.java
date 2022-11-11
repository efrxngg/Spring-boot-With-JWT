package com.empresax.security.domain.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresax.security.domain.service.IUserEntityService;
import com.empresax.security.persistence.entity.UserEntity;
import com.empresax.security.persistence.repository.IUserEnitityCrudRepository;

@Service
@Transactional
public class UserEntityServiceImpl implements IUserEntityService {

    @Autowired
    private IUserEnitityCrudRepository userEntityCrudRepository;

    @Override
    public UserEntity create(UserEntity entity) {
        return userEntityCrudRepository.save(entity);
    }

    @Override
    public UserEntity findById(UUID id) {
        return null;
    }

    @Override
    public List<UserEntity> findAll() {
        return userEntityCrudRepository.findAll();
    }

    @Override
    public UserEntity update(UserEntity entity) {
        userEntityCrudRepository.save(entity);
        return entity;
    }

    @Override
    public void delete(UUID id) {
    }

}
