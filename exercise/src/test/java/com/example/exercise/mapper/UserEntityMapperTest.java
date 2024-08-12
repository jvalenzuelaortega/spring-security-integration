package com.example.exercise.mapper;

import com.example.exercise.dto.request.PhoneRequestDto;
import com.example.exercise.dto.request.UserRequestDto;
import com.example.exercise.entity.UserEntity;
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
                .name("John Doe")
                .email("john.doe@example.com")
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

}