package com.empresax.security.web.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.security.domain.service.IUserEntityService;
import com.empresax.security.persistence.entity.UserEntity;
import com.empresax.security.web.UserEntityApi;

@RestController
@RequestMapping(value = "/api/admin/user/v1")
public class UserEntityRestController implements UserEntityApi {

    @Autowired
    private IUserEntityService userEntityService;

    @Override
    public ResponseEntity<UserEntity> createUserEntity(@RequestBody UserEntity userEntity) {
        return new ResponseEntity<>(userEntityService.create(userEntity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<UserEntity>> findAllUsersEntity() {
        return new ResponseEntity<>(userEntityService.findAll(), HttpStatus.OK);
    }

}
