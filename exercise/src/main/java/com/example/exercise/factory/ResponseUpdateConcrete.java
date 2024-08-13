package com.example.exercise.factory;

import com.example.exercise.dto.response.ResponseBaseDto;
import com.example.exercise.dto.response.ResponseUpdateDto;
import com.example.exercise.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUpdateConcrete implements ResponseBuilder {

    @Override
    public ResponseBaseDto buildResponse(UserEntity userEntity) {
        return ResponseUpdateDto.builder()
                .username(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }
}
