package com.empresax.security.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.empresax.security.persistence.repository.IUserEnitityCrudRepository;

// 2
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUserEnitityCrudRepository userEnitityCrudRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
         * if it throws the exception UsernameNotFoundException if performs a search again
         */
        return new UserDetailsImpl(userEnitityCrudRepository.findByUsername(username).get());
    }

}