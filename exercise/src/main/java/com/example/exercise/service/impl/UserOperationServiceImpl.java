package com.example.exercise.service.impl;

import com.example.exercise.dto.request.UserRequestDto;
import com.example.exercise.dto.request.UserUpdateRequestDto;
import com.example.exercise.entity.UserEntity;
import com.example.exercise.enums.ResponseType;
import com.example.exercise.exceptions.UserOperationException;
import com.example.exercise.factory.ResponseBuilder;
import com.example.exercise.dto.response.ResponseBaseDto;
import com.example.exercise.factory.ResponseDtoFactory;
import com.example.exercise.mapper.UserEntityMapper;
import com.example.exercise.repository.UserRepository;
import com.example.exercise.service.UserOperationService;
import com.example.exercise.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
