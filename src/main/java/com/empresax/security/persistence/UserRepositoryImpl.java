package com.empresax.security.persistence;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.empresax.security.domain.User;
import com.empresax.security.domain.repository.IUserRepository;
import com.empresax.security.persistence.config.Mapper;
import com.empresax.security.persistence.entity.StateType;
import com.empresax.security.persistence.entity.UserEntity;
import com.empresax.security.persistence.repository.IRolEntityCrudRepository;
import com.empresax.security.persistence.repository.IUserEnitityCrudRepository;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private IUserEnitityCrudRepository userEnitityCrudRepository;

    @Autowired
    private IRolEntityCrudRepository rolEntityCrudRepository;

    @Override
    public User save(User entity) {
        UserEntity userEntity = Mapper.toUserEntity(entity);
        userEntity.setDate_entry(new Date(System.currentTimeMillis()));
        userEntity.setRoles(Set.of(rolEntityCrudRepository.findByName("USER").get()));
        userEntity.setState(StateType.ACTIVE);
        entity.setId_user(
                userEnitityCrudRepository.save(userEntity).getId_user());
        return entity;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.of(Mapper.toUser(userEnitityCrudRepository.findById(id).get()));
    }

    @Override
    public List<User> findAll() {
        return userEnitityCrudRepository.findAll().stream()
                .map(Mapper::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public User update(User entity) {
        userEnitityCrudRepository.sp_UpdateUser(
                entity.getId_user(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getFist_name(),
                entity.getLast_name(),
                entity.getEmail(),
                entity.getPhone_number());
        return entity;
    }

    @Override
    public void delete(UUID id) {
        if(userEnitityCrudRepository.existsById(id));
            // throw exception
        userEnitityCrudRepository.sp_LogicalDeletionUser(id);
    }

}
