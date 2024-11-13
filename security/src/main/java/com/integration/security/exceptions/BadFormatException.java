package com.integration.security.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadFormatException extends RuntimeException {

    private final String message;

    public BadFormatException(String message) {
        this.message = message;
    }
}
