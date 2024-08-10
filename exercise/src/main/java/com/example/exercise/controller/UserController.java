package com.example.exercise.controller;

import com.example.exercise.dto.request.UserRequestDto;
import com.example.exercise.dto.response.UserResponseDto;
import com.example.exercise.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercise/api")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.createUser(userRequestDto);

        logger.info("User {} created !!!", userRequestDto.getName());

        return ResponseEntity.ok().body(userResponseDto);
    }

}
