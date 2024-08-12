package com.example.exercise.factory;

import com.example.exercise.entity.UserEntity;
import com.example.exercise.dto.response.ResponseBaseDto;

public interface ResponseBuilder {

    ResponseBaseDto buildResponse(UserEntity userEntity);
}
