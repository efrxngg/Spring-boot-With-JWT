package com.empresax.security.domain.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.empresax.security.persistence.UserDetailtRepository;
import com.empresax.security.web.rest.exception.UserNotFountException;

// 2
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailtRepository userDetailtRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var password = userDetailtRepository.findPasswordByUsername(username).orElseThrow(
                () -> new UserNotFountException(new StringBuilder("Username ").append(username)
                        .append(" doesn't exist or misspelled").toString()));

        return new User(
                username,
                password,
                Collections.emptySet());
    }

}