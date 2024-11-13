package com.integration.security.service.impl;

import com.integration.security.dto.request.PhoneRequestDto;
import com.integration.security.dto.request.UserRequestDto;
import com.integration.security.dto.request.UserUpdateRequestDto;
import com.integration.security.entity.UserEntity;
import com.integration.security.exceptions.UserOperationException;
import com.integration.security.dto.response.ResponseBaseDto;
import com.integration.security.repository.UserRepository;
import com.integration.security.utils.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import static org.mockito.ArgumentMatchers.anyString;
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

    @DisplayName("Create user in bd returns response dto")
    @Test
    void createUser_whenUserIsCreatedSuccessfully_thenReturnsResponseBaseDto_() {
        // Arrange
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
        when(userRepository.findUserByEmail(userRequestDto.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUserEntity);

        // Act
        ResponseBaseDto response = userOperationService.createUser(userRequestDto);

        // Assert
        assertNotNull(response);
        assertEquals("token", response.getToken());

        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(jwtUtils, times(1)).getToken(any(UserRequestDto.class));
    }

    @DisplayName("Create user in bd return user exception")
    @Test
    void createUser_whenRepositoryThrowsException_thenReturnUserOperationException() {
        // Arrange
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
        when(jwtUtils.getToken(any(UserRequestDto.class))).thenReturn("token");
        when(userRepository.save(any(UserEntity.class))).thenThrow(new RuntimeException("Error"));

        // Act
        assertThrows(UserOperationException.class, () -> userOperationService.createUser(userRequestDto));

        // Assert
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @DisplayName("Create user in bd when user already exist return user exception")
    @Test
    void createUser_whenUserAlreadyExistInBd_thenReturnUserOperationException() {
        // Arrange
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
        when(jwtUtils.getToken(any(UserRequestDto.class))).thenReturn("token");
        when(userRepository.findUserByEmail(userRequestDto.getEmail())).thenReturn(Optional.of(new UserEntity()));

        // Act
        assertThrows(UserOperationException.class, () -> userOperationService.createUser(userRequestDto));

        // Assert
        verify(userRepository, times(1)).findUserByEmail(anyString());
    }

    @DisplayName("Get user by name and response type return response dto")
    @Test
    void getUserByNameAndResponseType_whenUserExistsInDatabase_thenReturnsResponseBaseDto() throws UserOperationException {
        // Arrange
        String name = "test";
        String typeResponse = "default";
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);

        when(userRepository.findUserByName(name)).thenReturn(Optional.of(userEntity));

        // Act
        ResponseBaseDto response = userOperationService.getUserByNameAndResponseType(name, typeResponse);

        // Assert
        assertNotNull(response);
        verify(userRepository, times(1)).findUserByName(name);
    }

    @DisplayName("Get user by name and response type return user exception")
    @Test
    void getUserByNameAndResponseType_whenUserNotFound_thenReturnUserOperationException_() {
        // Arrange
        String name = "NonExistentUser";
        String typeResponse = "USER_DEFAULT";

        when(userRepository.findUserByName(name)).thenReturn(Optional.empty());

        // Act
        assertThrows(UserOperationException.class, () -> userOperationService.getUserByNameAndResponseType(name, typeResponse));

        // Assert
        verify(userRepository, times(1)).findUserByName(name);
    }

    @DisplayName("Get user by name and null response type return exception")
    @Test
    void getUserByNameAndResponseType_whenInvalidResponseType_thenReturnIllegalArgumentException() {
        String name = "test";
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);

        when(userRepository.findUserByName(name)).thenReturn(Optional.of(userEntity));

        assertThrows(IllegalArgumentException.class, () -> userOperationService.getUserByNameAndResponseType(name, null));
        verify(userRepository, times(1)).findUserByName(name);
    }

    @DisplayName("Create user when user already exists throws UserOperationException")
    @Test
    void createUser_whenUserAlreadyExists_thenThrowsUserOperationException() {
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
        when(userRepository.findUserByEmail(userRequestDto.getEmail())).thenReturn(Optional.of(new UserEntity()));

        assertThrows(UserOperationException.class, () -> userOperationService.createUser(userRequestDto));
        verify(userRepository, times(1)).findUserByEmail(userRequestDto.getEmail());
    }

    @DisplayName("Update user email and password when user not found throws UserOperationException")
    @Test
    void updateUserEmailAndPassword_whenUserNotFound_thenThrowsUserOperationException() {
        // Arrange
        UserUpdateRequestDto userUpdateRequestDto = UserUpdateRequestDto.builder()
                .email("newemail@test.com")
                .password("NewPassword123")
                .build();
        when(userRepository.findUserByEmail(userUpdateRequestDto.getEmail())).thenReturn(Optional.empty());

        // Act
        assertThrows(UserOperationException.class, () -> userOperationService.updateMailAndPasswordOfUser(userUpdateRequestDto));

        // Assert
        verify(userRepository, times(1)).findUserByName(userUpdateRequestDto.getName());
    }

    @DisplayName("Update user email and password when update fails throws UserOperationException")
    @Test
    void updateUserEmailAndPassword_whenUpdateFails_thenThrowsUserOperationException() {
        // Assert
        UserUpdateRequestDto userUpdateRequestDto = UserUpdateRequestDto.builder()
                .email("newemail@test.com")
                .password("NewPassword123")
                .build();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(new UUID(1, 1));
        when(userRepository.findUserByName(userUpdateRequestDto.getName())).thenReturn(Optional.of(userEntity));
        when(userRepository.updateEmailAndPasswordByUserId(userEntity.getId(), userUpdateRequestDto.getEmail(), userUpdateRequestDto.getPassword())).thenReturn(0);

        // Act
        assertThrows(UserOperationException.class, () -> userOperationService.updateMailAndPasswordOfUser(userUpdateRequestDto));

        // Assert
        verify(userRepository, times(1)).findUserByName(userUpdateRequestDto.getName());
        verify(userRepository, times(1)).updateEmailAndPasswordByUserId(userEntity.getId(), userUpdateRequestDto.getEmail(), userUpdateRequestDto.getPassword());
    }

}