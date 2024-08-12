package com.example.exercise.mapper;

import com.example.exercise.dto.request.PhoneRequestDto;
import com.example.exercise.dto.request.UserRequestDto;
import com.example.exercise.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityMapperTest {

    @Test
    void mapperToUserEntity_ReturnsUserEntity_WhenValidUserRequestDto() {
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

        UserEntity userEntity = UserEntityMapper.mapperToUserEntity(userRequestDto);

        assertNotNull(userEntity);
        assertEquals(userRequestDto.getName(), userEntity.getName());
        assertEquals(userRequestDto.getEmail(), userEntity.getEmail());
        assertEquals(userRequestDto.getPassword(), userEntity.getPassword());
        assertEquals(userRequestDto.getPhones().size(), userEntity.getPhoneEntities().size());
    }

    @Test
    void mapperToUserEntity_ReturnsUserEntity_WhenUserRequestDtoHasNoPhones() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password123")
                .phones(Collections.emptyList())
                .build();

        UserEntity userEntity = UserEntityMapper.mapperToUserEntity(userRequestDto);

        assertNotNull(userEntity);
        assertEquals(userRequestDto.getName(), userEntity.getName());
        assertEquals(userRequestDto.getEmail(), userEntity.getEmail());
        assertEquals(userRequestDto.getPassword(), userEntity.getPassword());
        assertTrue(userEntity.getPhoneEntities().isEmpty());
    }

}