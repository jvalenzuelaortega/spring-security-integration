package com.example.exercise.service.impl;

import com.example.exercise.controller.UserController;
import com.example.exercise.dto.request.UserRequestDto;
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

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserOperationServiceImpl implements UserOperationService {

    Logger logger = LoggerFactory.getLogger(UserOperationServiceImpl.class);

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public UserOperationServiceImpl(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseBaseDto createUser(UserRequestDto userRequestDto) {

        boolean userExists = validateUserInDatabase(userRequestDto.getEmail());
        if (userExists) {
            throw new UserOperationException("User already exists in database");
        }

        UserEntity userEntityMapped = UserEntityMapper.mapperToUserEntity(userRequestDto);
        userEntityMapped.setToken(jwtUtils.getToken(userRequestDto));
        userEntityMapped.setActive(true);

        try {
            UserEntity userEntitySaved = userRepository.save(userEntityMapped);
            logger.info("User {} saved !!!", userRequestDto.getName());

            ResponseBuilder responseBuilder = ResponseDtoFactory.getResponseBuilder(ResponseType.USER_DEFAULT);
            logger.info("[createUser] [ResponseBuilder is type : {}]", responseBuilder);
            return responseBuilder.buildResponse(userEntitySaved);
        } catch (Exception e) {
            logger.error("Error saving user in database {}", e.getMessage());
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

    private boolean validateUserInDatabase(String email) {
        Optional<UserEntity> userEntity = userRepository.findUserByEmail(email);
        if (userEntity.isPresent()) {
            logger.info("User {} already exists in database", email);
            return true;
        }
        return false;

    }

}
