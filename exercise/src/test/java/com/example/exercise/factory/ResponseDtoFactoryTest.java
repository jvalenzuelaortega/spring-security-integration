package com.example.exercise.factory;

import com.example.exercise.enums.ResponseType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResponseDtoFactoryTest {

    @DisplayName("Get response builder returns response default concrete when type is user default")
    @Test
    void getResponseBuilder_whenResponseTypeIsDefault_thenReturnResponseBuilder() {
        // Act
        ResponseBuilder responseBuilder = ResponseDtoFactory.getResponseBuilder(ResponseType.USER_DEFAULT);

        // Assert
        assertNotNull(responseBuilder);
        assertTrue(responseBuilder instanceof ResponseDefaultConcrete);
    }

    @DisplayName("Get response builder returns response detail concrete when type is user detail")
    @Test
    void getResponseBuilder_whenResponseTypeIsDetail_thenReturnResponseBuilder() {
        // Act
        ResponseBuilder responseBuilder = ResponseDtoFactory.getResponseBuilder(ResponseType.USER_DETAIL);

        // Assert
        assertNotNull(responseBuilder);
        assertTrue(responseBuilder instanceof ResponseDetailConcrete);
    }

}