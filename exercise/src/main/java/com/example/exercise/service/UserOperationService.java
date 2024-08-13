package com.example.exercise.service;

import com.example.exercise.dto.request.UserRequestDto;
import com.example.exercise.dto.request.UserUpdateRequestDto;
import com.example.exercise.exceptions.UserOperationException;
import com.example.exercise.dto.response.ResponseBaseDto;

public interface UserOperationService {

    ResponseBaseDto createUser(UserRequestDto userRequestDto) throws UserOperationException;

    ResponseBaseDto getUserByNameAndResponseType(String name, String type);

    ResponseBaseDto updateMailAndPasswordOfUser(UserUpdateRequestDto userRequestDto) throws UserOperationException;
}
