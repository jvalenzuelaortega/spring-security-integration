package com.example.exercise.factory;

import com.example.exercise.entity.UserEntity;
import com.example.exercise.dto.response.ResponseBaseDto;
import com.example.exercise.dto.response.ResponseDetailsDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDetailConcrete implements ResponseBuilder{

    @Override
    public ResponseBaseDto buildResponse(UserEntity userEntity) {
        return ResponseDetailsDto.builder()
                .username(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .token(userEntity.getToken())
                .isActive(userEntity.isActive())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .phones(new ArrayList<>())
                .build();
    }
}
