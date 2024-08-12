package com.example.exercise.factory;

import com.example.exercise.enums.ResponseType;

public class ResponseDtoFactory {

    public static ResponseBuilder getResponseBuilder(ResponseType type) {
        return switch (type) {
            case USER_DEFAULT -> ResponseDefaultConcrete.builder().build();
            case USER_DETAIL -> ResponseDetailConcrete.builder().build();
        };
    }
}
