package com.example.exercise.enums;

import java.util.Arrays;

public enum ResponseType {

    USER_DEFAULT("default"),
    USER_DETAIL("detail"),
    USER_UPDATE("update");

    private final String value;

    ResponseType(String value) {
        this.value = value;
    }

    public static ResponseType getResponseType(final String value) {
        return Arrays.stream(values())
                .filter(responseType -> responseType.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid ResponseType value: %s", value)));
    }
}
