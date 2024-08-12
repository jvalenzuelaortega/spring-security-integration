package com.example.exercise.factory;

import com.example.exercise.enums.ResponseType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResponseDtoFactoryTest {

    @Test
    void getResponseBuilder_ReturnsResponseDefaultConcrete_WhenTypeIsUserDefault() {
        ResponseBuilder responseBuilder = ResponseDtoFactory.getResponseBuilder(ResponseType.USER_DEFAULT);
        assertNotNull(responseBuilder);
        assertTrue(responseBuilder instanceof ResponseDefaultConcrete);
    }

    @Test
    void getResponseBuilder_ReturnsResponseDetailConcrete_WhenTypeIsUserDetail() {
        ResponseBuilder responseBuilder = ResponseDtoFactory.getResponseBuilder(ResponseType.USER_DETAIL);
        assertNotNull(responseBuilder);
        assertTrue(responseBuilder instanceof ResponseDetailConcrete);
    }

}