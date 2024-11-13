package com.integration.security.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.integration.security.dto.request.UserRequestDto;
import com.integration.security.dto.request.UserUpdateRequestDto;
import com.integration.security.dto.response.ResponseBaseDto;
import com.integration.security.entity.UserEntity;
import com.integration.security.enums.ResponseType;
import com.integration.security.exceptions.UserOperationException;
import com.integration.security.factory.ResponseBuilder;
import com.integration.security.factory.ResponseDtoFactory;
import com.integration.security.mapper.UserEntityMapper;
import com.integration.security.repository.UserRepository;
import com.integration.security.service.UserOperationService;
import com.integration.security.utils.JwtUtils;

@Service
public class UserOperationServiceImpl implements UserOperationService {

    Logger logger = LoggerFactory.getLogger(UserOperationServiceImpl.class);

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public UserOperationServiceImpl(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    public ResponseBaseDto createUser(UserRequestDto userRequestDto) {

        if (validateUserInDatabase(userRequestDto.getEmail())) {
            throw new UserOperationException("User already exists in database");
        }

        UserEntity userEntity = UserEntityMapper.mapperToUserEntity(userRequestDto);
        userEntity.setToken(jwtUtils.getToken(userRequestDto));
        userEntity.setActive(true);

        userEntity.getPhoneEntities().forEach(phoneEntity -> phoneEntity.setUserEntity(userEntity));

        try {
            UserEntity savedUser = userRepository.save(userEntity);
            logger.info("[createUser] User {} saved successful in database", userRequestDto.getName());

            ResponseBuilder responseBuilder = ResponseDtoFactory.getResponseBuilder(ResponseType.USER_DEFAULT);
            logger.info("[createUser] [ResponseBuilder is type : {}]", responseBuilder);
            return responseBuilder.buildResponse(savedUser);
        } catch (Exception e) {
            logger.error("[createUser] [Error saving user in database {}]", e.getMessage());
            throw new UserOperationException("Error saving user in database");
        }
    }

    @Override
    public ResponseBaseDto getUserByNameAndResponseType(String name, String typeResponse) throws UserOperationException {
        UserEntity userEntity = userRepository.findUserByName(name)
                .orElseThrow(() -> new UserOperationException("User details not found in database"));

        ResponseType typeResponseDto = ResponseType.getResponseType(typeResponse);
        ResponseBuilder responseBuilder = ResponseDtoFactory.getResponseBuilder(typeResponseDto);
        logger.info("[getUserByNameAndResponseType] [ResponseBuilder is type : {}]", responseBuilder);

        return responseBuilder.buildResponse(userEntity);
    }

    @Override
    public ResponseBaseDto updateMailAndPasswordOfUser(UserUpdateRequestDto userUpdateRequestDto) throws UserOperationException {
        UserEntity userEntity = userRepository.findUserByName(userUpdateRequestDto.getName())
                .orElseThrow(() -> new UserOperationException("[updateUser] [User not found in database]"));

        int userRowsUpdate = userRepository.updateEmailAndPasswordByUserId(userEntity.getId(), userUpdateRequestDto.getEmail(), userUpdateRequestDto.getPassword());
        if (userRowsUpdate <= 0) {
            throw new UserOperationException(String.format("[updateUser] [User not updated, Rows updated %d]", userRowsUpdate));
        }

        logger.info("[updateMailAndPasswordOfUser] [User {} updated in database]", userEntity.getName());
        ResponseBuilder responseBuilder = ResponseDtoFactory.getResponseBuilder(ResponseType.USER_UPDATE);

        logger.info("[updateMailAndPasswordOfUser] [ResponseBuilder is type : {}]", responseBuilder);
        UserEntity userEntityMapped = UserEntityMapper.mapperToUserEntity(userUpdateRequestDto);
        return responseBuilder.buildResponse(userEntityMapped);
    }


    private boolean validateUserInDatabase(String email) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            logger.info("[createUser] User {} already exists in database", email);
            return true;
        }
        return false;
    }


}
