package com.empresax.security.domain.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.empresax.security.persistence.entity.StateType;
import com.empresax.security.persistence.entity.UserEntity;
import com.empresax.security.persistence.repository.IUserEnitityCrudRepository;

// 2
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUserEnitityCrudRepository userEnitityCrudRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userEnitityCrudRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username));

        return new User(
                username,
                user.getPassword(),
                user.getRoles().stream()
                        .filter(rol -> rol.getState().equals(StateType.ACTIVE))
                        .map(x -> new SimpleGrantedAuthority(x.getName()))
                        .collect(Collectors.toList()));
    }

}