package com.integration.security.mapper;


import org.modelmapper.ModelMapper;

import com.integration.security.dto.request.UserRequestDto;
import com.integration.security.dto.request.UserUpdateRequestDto;
import com.integration.security.entity.UserEntity;

public class UserEntityMapper {

    public static UserEntity mapperToUserEntity(UserRequestDto userRequestDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(UserRequestDto.class, UserEntity.class).addMappings(mapper -> {
            mapper.map(UserRequestDto::getName, UserEntity::setName);
            mapper.map(UserRequestDto::getEmail, UserEntity::setEmail);
            mapper.map(UserRequestDto::getPassword, UserEntity::setPassword);
            mapper.map(UserRequestDto::getPhones, UserEntity::setPhoneEntities);
        });

        return modelMapper.map(userRequestDto, UserEntity.class);
    }

    public static UserEntity mapperToUserEntity(UserUpdateRequestDto userUpdateRequestDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(UserUpdateRequestDto.class, UserEntity.class).addMappings(mapper -> {
            mapper.map(UserUpdateRequestDto::getEmail, UserEntity::setEmail);
            mapper.map(UserUpdateRequestDto::getPassword, UserEntity::setPassword);
            mapper.map(UserUpdateRequestDto::getName, UserEntity::setName);
        });

        return modelMapper.map(userUpdateRequestDto, UserEntity.class);
    }
}
