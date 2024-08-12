package com.example.exercise.controller;

import com.example.exercise.dto.request.PhoneRequestDto;
import com.example.exercise.dto.request.UserRequestDto;
import com.example.exercise.exceptions.UserOperationException;
import com.example.exercise.dto.response.ResponseBaseDto;
import com.example.exercise.dto.response.ResponseDefaultDto;
import com.example.exercise.service.UserOperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void createUserReturnsCreatedStatus() throws UserOperationException {
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
        ResponseBaseDto responseBaseDto = ResponseDefaultDto.builder()
                .id(new UUID(1, 1))
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("token")
                .isActive(true)
                .build();
        when(userOperationService.createUser(any(UserRequestDto.class))).thenReturn(responseBaseDto);

        ResponseEntity<ResponseBaseDto> response = userController.createUser(userRequestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseBaseDto, response.getBody());
    }

    @Test
    void createUserThrowsUserOperationException() {
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

        assertThrows(UserOperationException.class, () -> userController.createUser(userRequestDto));
    }

    @Test
    void getUserDetailsByNameAndTypeResponseReturnsOkStatus() throws UserOperationException {
        String name = "John";
        String typeResponse = "type";
        ResponseBaseDto responseBaseDto = ResponseDefaultDto.builder()
                .id(new UUID(1, 1))
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("token")
                .isActive(true)
                .build();
        when(userOperationService.getUserByNameAndResponseType(name, typeResponse)).thenReturn(responseBaseDto);

        ResponseEntity<?> response = userController.getUserDetailsByNameAndTypeResponse(typeResponse, name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseBaseDto, response.getBody());
    }

    @Test
    void getUserDetailsByNameAndTypeResponseThrowsUserOperationException() {
        String name = "John";
        String typeResponse = "type";
        when(userOperationService.getUserByNameAndResponseType(name, typeResponse)).thenThrow(new UserOperationException("Error"));

        assertThrows(UserOperationException.class, () -> userController.getUserDetailsByNameAndTypeResponse(typeResponse, name));
    }
}