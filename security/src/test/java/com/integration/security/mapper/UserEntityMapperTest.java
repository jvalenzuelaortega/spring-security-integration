package com.integration.security.mapper;

import com.integration.security.dto.request.PhoneRequestDto;
import com.integration.security.dto.request.UserRequestDto;
import com.integration.security.dto.request.UserUpdateRequestDto;
import com.integration.security.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserEntityMapperTest {

    @DisplayName("Mapper to user entity when valid user request dto then returns user entity")
    @Test
    void mapperToUserEntity_whenValidUserRequestDto_thenReturnsUserEntity() {
        // Arrange
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name("User name")
                .email("username@example.com")
                .password("password123")
                .phones(List.of(PhoneRequestDto.builder()
                        .number("1234567")
                        .cityCode("1234")
                        .countryCode("1234")
                        .build()))
                .build();

        // Act
        UserEntity userEntity = UserEntityMapper.mapperToUserEntity(userRequestDto);

        // Assert
        assertNotNull(userEntity);
        assertEquals(userRequestDto.getName(), userEntity.getName());
        assertEquals(userRequestDto.getEmail(), userEntity.getEmail());
        assertEquals(userRequestDto.getPassword(), userEntity.getPassword());
        assertEquals(userRequestDto.getPhones().size(), userEntity.getPhoneEntities().size());
    }

    @DisplayName("Mapper to user entity when valid user update request dto then returns user entity")
    @Test
    void mapperToUserEntity_whenValidUserUpdateRequestDto_thenReturnsUserEntity() {
        UserUpdateRequestDto userUpdateRequestDto = UserUpdateRequestDto.builder()
                .name("username")
                .email("username@example.com")
                .password("newpassword123")
                .build();

        UserEntity userEntity = UserEntityMapper.mapperToUserEntity(userUpdateRequestDto);

        assertNotNull(userEntity);
        assertEquals(userUpdateRequestDto.getName(), userEntity.getName());
        assertEquals(userUpdateRequestDto.getEmail(), userEntity.getEmail());
        assertEquals(userUpdateRequestDto.getPassword(), userEntity.getPassword());
    }

}