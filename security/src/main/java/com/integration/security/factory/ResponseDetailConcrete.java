package com.integration.security.factory;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.integration.security.dto.response.ResponseBaseDto;
import com.integration.security.dto.response.ResponseDetailsDto;
import com.integration.security.entity.UserEntity;
import com.integration.security.mapper.PhoneResponseMapper;
import com.integration.security.utils.DateUtils;

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
