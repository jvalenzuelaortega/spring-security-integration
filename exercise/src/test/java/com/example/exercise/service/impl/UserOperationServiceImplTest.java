package com.example.exercise.service.impl;

import com.example.exercise.dto.request.PhoneRequestDto;
import com.example.exercise.dto.request.UserRequestDto;
import com.example.exercise.entity.UserEntity;
import com.example.exercise.exceptions.UserOperationException;
import com.example.exercise.dto.response.ResponseBaseDto;
import com.example.exercise.repository.UserRepository;
import com.example.exercise.utils.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserOperationServiceImplTest {

    private UserOperationServiceImpl userOperationService;
    private UserRepository userRepository;
    private JwtUtils jwtUtils;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        jwtUtils = Mockito.mock(JwtUtils.class);
        userOperationService = new UserOperationServiceImpl(userRepository, jwtUtils);
    }

    @Test
    void createUser_ReturnsResponseBaseDto_WhenUserIsCreatedSuccessfully() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name("test")
                .email("test@email.com")
                .password("password")
                .phones(List.of(PhoneRequestDto.builder()
                        .number("1234567")
                        .cityCode("1234")
                        .countryCode("1234")
                        .build()))
                .build();

        UserEntity savedUserEntity = new UserEntity();
        savedUserEntity.setId(new UUID(1, 1));
        savedUserEntity.setCreatedAt(Instant.now());
        savedUserEntity.setUpdatedAt(Instant.now());
        savedUserEntity.setToken("token");

        when(jwtUtils.getToken(any(UserRequestDto.class))).thenReturn("token");
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUserEntity);

        ResponseBaseDto response = userOperationService.createUser(userRequestDto);

        assertNotNull(response);
        assertEquals("token", response.getToken());

        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(jwtUtils, times(1)).getToken(any(UserRequestDto.class));
    }

    @Test
    void createUser_ThrowsUserOperationException_WhenRepositoryThrowsException() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name("test")
                .email("test@mail.com")
                .password("password")
                .phones(List.of(PhoneRequestDto.builder()
                        .number("1234567")
                        .cityCode("1234")
                        .countryCode("1234")
                        .build()))
                .build();
        Mockito.when(jwtUtils.getToken(any(UserRequestDto.class))).thenReturn("token");
        Mockito.when(userRepository.save(any(UserEntity.class))).thenThrow(new RuntimeException("Error"));

        assertThrows(UserOperationException.class, () -> userOperationService.createUser(userRequestDto));

        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void getUserByNameAndResponseType_ReturnsResponseBaseDto_WhenUserExists() throws UserOperationException {
        String name = "John";
        String typeResponse = "default";
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);

        when(userRepository.findUserByName(name)).thenReturn(Optional.of(userEntity));

        ResponseBaseDto response = userOperationService.getUserByNameAndResponseType(name, typeResponse);

        assertNotNull(response);
        verify(userRepository, times(1)).findUserByName(name);
    }

    @Test
    void getUserByNameAndResponseType_ThrowsUserOperationException_WhenUserNotFound() {
        String name = "NonExistentUser";
        String typeResponse = "USER_DEFAULT";

        when(userRepository.findUserByName(name)).thenReturn(Optional.empty());

        assertThrows(UserOperationException.class, () -> userOperationService.getUserByNameAndResponseType(name, typeResponse));
        verify(userRepository, times(1)).findUserByName(name);
    }

    @Test
    void getUserByNameAndResponseType_ThrowsUserOperationException_WhenInvalidResponseType() {
        String name = "John";
        String typeResponse = null;
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);

        when(userRepository.findUserByName(name)).thenReturn(Optional.of(userEntity));

        assertThrows(IllegalArgumentException.class, () -> userOperationService.getUserByNameAndResponseType(name, typeResponse));
        verify(userRepository, times(1)).findUserByName(name);
    }

}