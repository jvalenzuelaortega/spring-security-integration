package com.integration.security.factory;

import com.integration.security.enums.ResponseType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @DisplayName("Get response builder returns response update concrete when type is user update")
    @Test
    void getResponseBuilder_whenResponseTypeIsUpdate_thenReturnResponseBuilder() {
        // Act
        ResponseBuilder responseBuilder = ResponseDtoFactory.getResponseBuilder(ResponseType.USER_UPDATE);

        // Assert
        assertNotNull(responseBuilder);
        assertTrue(responseBuilder instanceof ResponseUpdateConcrete);
    }

    @DisplayName("Get response builder throws exception when type is null")
    @Test
    void getResponseBuilder_whenResponseTypeIsNull_thenThrowException() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> ResponseDtoFactory.getResponseBuilder(null));
    }

}