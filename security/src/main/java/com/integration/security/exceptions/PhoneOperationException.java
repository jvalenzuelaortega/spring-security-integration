package com.integration.security.exceptions;

import lombok.Getter;

@Getter
public class PhoneOperationException extends RuntimeException {

    private final String message;

    public PhoneOperationException(String message) {
        this.message = message;
    }
}
