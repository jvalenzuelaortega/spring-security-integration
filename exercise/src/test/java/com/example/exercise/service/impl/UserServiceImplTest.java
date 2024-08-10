package com.example.exercise.service.impl;

import com.example.exercise.dto.request.PhoneRequestDto;
import com.example.exercise.dto.request.UserRequestDto;
import com.example.exercise.dto.response.UserResponseDto;
import com.example.exercise.entity.Phone;
import com.example.exercise.entity.User;
import com.example.exercise.repository.PhoneRepository;
import com.example.exercise.repository.UserRepository;
import com.example.exercise.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void createUser_thenPersistDataInBd_thenReturnResponseDto() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("John Doe");
        userRequestDto.setEmail("john.doe@example.com");
        userRequestDto.setPassword("Password123!");

        PhoneRequestDto phoneRequestDto = new PhoneRequestDto();
        phoneRequestDto.setNumber("123456789");
        phoneRequestDto.setCityCode("1");
        phoneRequestDto.setCountryCode("1");
        userRequestDto.setPhones(Collections.singletonList(phoneRequestDto));

        User user = new User();
        user.setId(new UUID(1, 1));
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("Password123!");

        Phone phone = new Phone();
        phone.setNumber("123456789");
        phone.setCityCode("1");
        phone.setCountryCode("1");
        phone.setUser(user);

        User userCreated = new User();
        userCreated.setId(new UUID(1, 1));
        userCreated.setName("John Doe");
        userCreated.setEmail("john.doe@example.com");
        userCreated.setPassword("Password123!");

        Phone phoneCreated = new Phone();
        phoneCreated.setNumber("123456789");
        phoneCreated.setCityCode("1");
        phoneCreated.setCountryCode("1");
        phoneCreated.setUser(user);

        when(userRepository.save(user)).thenReturn(userCreated);
        when(phoneRepository.save(phone)).thenReturn(phoneCreated);

        UserResponseDto userResponseDto = userService.createUser(userRequestDto);

        assertEquals(1L, userResponseDto.getId());
        assertEquals("John Doe", userResponseDto.getCreated());
        assertEquals("john.doe@example.com", userResponseDto.getToken());
        assertTrue(userResponseDto.isActive());
    }

}