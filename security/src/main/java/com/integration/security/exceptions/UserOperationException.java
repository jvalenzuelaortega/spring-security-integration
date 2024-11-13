package com.integration.security.exceptions;

import lombok.Getter;

@Getter
public class UserOperationException extends RuntimeException {

    private final String message;

    public UserOperationException(String message) {
        this.message = message;
    }

}
