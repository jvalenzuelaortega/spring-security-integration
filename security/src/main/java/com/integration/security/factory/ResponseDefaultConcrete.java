package com.integration.security.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.integration.security.dto.response.ResponseBaseDto;
import com.integration.security.dto.response.ResponseDefaultDto;
import com.integration.security.entity.UserEntity;
import com.integration.security.utils.DateUtils;

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
