package com.integration.security.service;

import com.integration.security.dto.request.UserRequestDto;
import com.integration.security.dto.request.UserUpdateRequestDto;
import com.integration.security.dto.response.ResponseBaseDto;
import com.integration.security.exceptions.UserOperationException;

public interface UserOperationService {

    ResponseBaseDto createUser(UserRequestDto userRequestDto) throws UserOperationException;

    ResponseBaseDto getUserByNameAndResponseType(String name, String type);

    ResponseBaseDto updateMailAndPasswordOfUser(UserUpdateRequestDto userRequestDto) throws UserOperationException;
}
