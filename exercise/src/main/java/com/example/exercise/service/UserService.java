package com.example.exercise.service;

import com.example.exercise.dto.request.UserRequestDto;
import com.example.exercise.dto.response.UserResponseDto;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);
}
