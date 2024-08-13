package com.example.exercise.controller;

import com.example.exercise.dto.request.PhoneRequestDto;
import com.example.exercise.dto.request.UserRequestDto;
import com.example.exercise.dto.request.UserUpdateRequestDto;
import com.example.exercise.exceptions.UserOperationException;
import com.example.exercise.dto.response.ResponseBaseDto;
import com.example.exercise.dto.response.ResponseDefaultDto;
import com.example.exercise.service.UserOperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserOperationService userOperationService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Create user returns created status")
    @Test
    void createUser_whenBodyIsCorrect_thenReturnsCreatedStatus() throws UserOperationException {
        // Arrange
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name("test")
                .email("test@email.com")
                .password("Test1234")
                .phones(List.of(PhoneRequestDto.builder()
                        .number("1234567")
                        .cityCode("1")
                        .countryCode("57")
                        .build()))
                .build();
        ResponseBaseDto responseBaseDto = ResponseDefaultDto.builder()
                .id(new UUID(1, 1))
                .created("2024-08-13 11:14:50")
                .modified("2024-08-13 11:14:50")
                .lastLogin("2024-08-13 11:14:50")
                .token("token")
                .isActive(true)
                .build();
        when(userOperationService.createUser(any(UserRequestDto.class))).thenReturn(responseBaseDto);

        // Act
        ResponseEntity<ResponseBaseDto> response = userController.createUser(userRequestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseBaseDto, response.getBody());
    }

    @DisplayName("Create user then return throws UserOperationException")
    @Test
    void createUser_WhenExistException_thenLaunchUserOperationException() {
        // Arrange
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name("test")
                .email("test@email.com")
                .password("test1234")
                .phones(List.of(PhoneRequestDto.builder()
                        .number("1234567")
                        .cityCode("1")
                        .countryCode("57")
                        .build()))
                .build();
        when(userOperationService.createUser(any(UserRequestDto.class))).thenThrow(new UserOperationException("Error"));

        // Assert
        assertThrows(UserOperationException.class, () -> userController.createUser(userRequestDto));
    }

    @DisplayName("Get user details by name and type default response returns ok status")
    @Test
    void getUser_whenByNameAndTypeDefaultIsCorrect_thenReturnsOkStatus() throws UserOperationException {
        // Arrange
        String name = "John";
        String typeResponse = "default";
        ResponseBaseDto responseBaseDto = ResponseDefaultDto.builder()
                .id(new UUID(1, 1))
                .created("2024-08-13 11:14:50")
                .modified("2024-08-13 11:14:50")
                .lastLogin("2024-08-13 11:14:50")
                .token("token")
                .isActive(true)
                .build();
        when(userOperationService.getUserByNameAndResponseType(name, typeResponse)).thenReturn(responseBaseDto);

        // Act
        ResponseEntity<?> response = userController.getUserDetailsByNameAndTypeResponse(typeResponse, name);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseBaseDto, response.getBody());
    }

    @DisplayName("Get user by name and type detail response returns ok status")
    @Test
    void getUser_whenNameAndTypeDetailIsCorrect_thenReturnsOkStatus() throws UserOperationException {
        // Arrange
        String name = "John";
        String typeResponse = "detail";
        ResponseBaseDto responseBaseDto = ResponseDefaultDto.builder()
                .id(new UUID(1, 1))
                .created("2024-08-13 11:14:50")
                .modified("2024-08-13 11:14:50")
                .lastLogin("2024-08-13 11:14:50")
                .token("token")
                .isActive(true)
                .build();
        when(userOperationService.getUserByNameAndResponseType(name, typeResponse)).thenReturn(responseBaseDto);

        // Act
        ResponseEntity<?> response = userController.getUserDetailsByNameAndTypeResponse(typeResponse, name);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseBaseDto, response.getBody());
    }

    @DisplayName("Get user details by name and type response throws UserOperationException")
    @Test
    void getUser_whenNameAndTypeResponseIsCorrect_thenReturnThrowsUserOperationException() {
        // Arrange
        String name = "John";
        String typeResponse = "type";
        when(userOperationService.getUserByNameAndResponseType(name, typeResponse)).thenThrow(new UserOperationException("Error"));

        // Act
        assertThrows(UserOperationException.class, () -> userController.getUserDetailsByNameAndTypeResponse(typeResponse, name));
    }

    @DisplayName("Update user email and password returns ok status")
    @Test
    void updateUserEmailAndPassword_whenBodyIsCorrect_thenReturnsOkStatus() throws UserOperationException {
        // Arrange
        String name = "user";
        UserUpdateRequestDto userUpdateRequestDto = UserUpdateRequestDto.builder()
                .email("newemail@test.com")
                .password("NewPassword123")
                .build();
        ResponseBaseDto responseBaseDto = ResponseDefaultDto.builder()
                .id(new UUID(1, 1))
                .created("2024-08-13 11:14:50")
                .modified("2024-08-13 11:14:50")
                .lastLogin("2024-08-13 11:14:50")
                .token("token")
                .isActive(true)
                .build();
        when(userOperationService.updateMailAndPasswordOfUser(any(UserUpdateRequestDto.class))).thenReturn(responseBaseDto);

        // Act
        ResponseEntity<ResponseBaseDto> response = userController.updateEmailAndPasswordOfUser(name, userUpdateRequestDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseBaseDto, response.getBody());
    }

    @DisplayName("Update user email and password throws UserOperationException")
    @Test
    void updateUserEmailAndPassword_whenExceptionThrown_thenThrowsUserOperationException() {
        // Act
        String name = "user";
        UserUpdateRequestDto userUpdateRequestDto = UserUpdateRequestDto.builder()
                .email("newemail@test.com")
                .password("NewPassword123")
                .build();
        when(userOperationService.updateMailAndPasswordOfUser(any(UserUpdateRequestDto.class))).thenThrow(new UserOperationException("Error"));

        // Assert
        assertThrows(UserOperationException.class, () -> userController.updateEmailAndPasswordOfUser(name, userUpdateRequestDto));
    }
}