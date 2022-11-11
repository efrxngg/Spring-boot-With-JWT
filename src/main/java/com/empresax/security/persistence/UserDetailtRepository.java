package com.empresax.security.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.empresax.security.persistence.repository.IUserEnitityCrudRepository;

@Repository
public class UserDetailtRepository {

    @Autowired
    private IUserEnitityCrudRepository userEnitityCrudRepository;

    public Optional<String> findPasswordByUsername(String username) {
        return Optional.of(userEnitityCrudRepository.findPasswordByUsername(username).get());
    }

}
