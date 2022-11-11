package com.empresax.security.web.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.security.domain.dto.UserAuthenticationDTO;
import com.empresax.security.web.AuthenticationApi;
import com.empresax.security.web.security.jwt.JwtUtil;

// 4
@RestController
@RequestMapping(value = "/api/auth/v1")
public class AuthenticationRestController implements AuthenticationApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<String> clainToken(@RequestBody UserAuthenticationDTO user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        String token = JwtUtil.generateToken(user.getUsername());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
    
}
