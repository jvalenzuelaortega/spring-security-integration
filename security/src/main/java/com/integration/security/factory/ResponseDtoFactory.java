package com.integration.security.factory;

import com.integration.security.enums.ResponseType;

public class ResponseDtoFactory {

    public static ResponseBuilder getResponseBuilder(ResponseType type) {
        return switch (type) {
            case USER_DEFAULT -> ResponseDefaultConcrete.builder().build();
            case USER_DETAIL -> ResponseDetailConcrete.builder().build();
            case USER_UPDATE -> ResponseUpdateConcrete.builder().build();
        };
    }
}
