package com.empresax.security.web.rest.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFountException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserNotFountException(String msg) {
        super(msg);
    }

}
