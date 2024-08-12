package com.example.exercise.factory;

import com.example.exercise.entity.UserEntity;
import com.example.exercise.dto.response.ResponseBaseDto;
import com.example.exercise.dto.response.ResponseDefaultDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDefaultConcrete implements ResponseBuilder {

    @Override
    public ResponseBaseDto buildResponse(UserEntity userEntity) {
        return ResponseDefaultDto.builder()
                .id(userEntity.getId())
                .created(null)
                .modified(null)
                .lastLogin(null)
                .token(userEntity.getToken())
                .isActive(userEntity.isActive())
                .build();
    }
}
