package com.example.exercise.factory;

import com.example.exercise.entity.UserEntity;
import com.example.exercise.dto.response.ResponseBaseDto;
import com.example.exercise.dto.response.ResponseDefaultDto;
import com.example.exercise.utils.DateUtils;
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
                .username(userEntity.getName())
                .created(DateUtils.getLocalDateTime(userEntity.getCreatedAt()))
                .modified(DateUtils.getLocalDateTime(userEntity.getUpdatedAt()))
                .lastLogin(DateUtils.getLocalDateTime(userEntity.getCreatedAt()))
                .token(userEntity.getToken())
                .isActive(userEntity.isActive())
                .build();
    }
}
