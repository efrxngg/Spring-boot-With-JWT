package com.empresax.security.web.rest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.security.domain.User;
import com.empresax.security.domain.service.IUserService;
import com.empresax.security.web.UserApi;

@RestController
@RequestMapping(value = "/api/users/v1")
public class UserRestController implements UserApi {

    @Autowired
    private IUserService userService;

    @Override
    public ResponseEntity<User> createUser(User User) {
        return new ResponseEntity<>(userService.create(User), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> findUserById(@PathVariable UUID id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> updateUserById(User User) {
        return new ResponseEntity<>(userService.update(User), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
