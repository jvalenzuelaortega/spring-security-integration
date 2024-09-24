package com.integration.security.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.integration.security.dto.response.ResponseBaseDto;
import com.integration.security.dto.response.ResponseUpdateDto;
import com.integration.security.entity.UserEntity;

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
