package com.empresax.security.domain.api;

import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.empresax.security.domain.dto.UserAuthenticationDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Primary
@Tag(name = "Authentication", description = "Authentication required for other endpoints")
public interface AuthenticationApi {

    @Operation(summary = "Claim Token")
    @PostMapping
    public ResponseEntity<String> clainToken(UserAuthenticationDTO user);

}
