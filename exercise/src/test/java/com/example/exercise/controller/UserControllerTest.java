package com.example.exercise.controller;

import com.example.exercise.configuration.RegexConfig;
import com.example.exercise.dto.request.UserRequestDto;
import com.example.exercise.dto.response.UserResponseDto;
import com.example.exercise.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserControllerTest.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private RegexConfig regexConfig;

    @BeforeEach
    void setUp() {
        when(regexConfig.getMyFieldRegex()).thenReturn("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

    @Test
    void createUser_whenUserHaveCorrectData_thenReturnResponseDto() throws Exception {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("John Doe");
        userRequestDto.setEmail("test@email.com");
        userRequestDto.setPassword("Password123!");

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(new UUID(0, 1));
        userResponseDto.setCreated(null);
        userResponseDto.setModified(null);
        userResponseDto.setLastLogin(null);
        userResponseDto.setToken("token");
        userResponseDto.setActive(true);

        when(userService.createUser(userRequestDto)).thenReturn(userResponseDto);

        mockMvc.perform(post("/exercise/api/create-user")
                        .contentType("application/json")
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"password\":\"Password123!\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void createUser_whenUserHaveBadEmail_thenReturnException() throws Exception {
        mockMvc.perform(post("/exercise/api/create-user")
                        .contentType("application/json")
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"bad-email\",\"password\":\"Password123!\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUser_whenUserHaveBadFormatPassword_thenReturnException() throws Exception {
        mockMvc.perform(post("/exercise/api/create-user")
                        .contentType("application/json")
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"password\":\"badpassword\"}"))
                .andExpect(status().isBadRequest());
    }

}