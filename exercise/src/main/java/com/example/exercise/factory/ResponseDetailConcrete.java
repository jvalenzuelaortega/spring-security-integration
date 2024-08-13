package com.example.exercise.factory;

import com.example.exercise.entity.UserEntity;
import com.example.exercise.dto.response.ResponseBaseDto;
import com.example.exercise.dto.response.ResponseDetailsDto;
import com.example.exercise.mapper.PhoneResponseMapper;
import com.example.exercise.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

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
                .created(DateUtils.getLocalDateTime(userEntity.getCreatedAt()))
                .modified(DateUtils.getLocalDateTime(userEntity.getUpdatedAt()))
                .phones(PhoneResponseMapper.mapperToResponseDetailPhoneDto(userEntity.getPhoneEntities()))
                .build();
    }
}
