package com.integration.security.factory;

import com.integration.security.dto.response.ResponseBaseDto;
import com.integration.security.entity.UserEntity;

public interface ResponseBuilder {

    ResponseBaseDto buildResponse(UserEntity userEntity);
}
