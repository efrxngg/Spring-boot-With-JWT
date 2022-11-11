package com.empresax.security.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.empresax.security.persistence.entity.UserEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserEntity", description = "All crud operations for Users")
@SecurityRequirement(name = "Bearer Authentication")
public interface UserEntityApi {

    @Operation(summary = "Create a new User")
    @PostMapping
    public ResponseEntity<UserEntity> createUserEntity(UserEntity userEntity);

    @Operation(summary = "Find all Users")
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserEntity>> findAllUsersEntity();

}
