package com.example.exercise.mapper;

import com.example.exercise.dto.request.UserRequestDto;
import com.example.exercise.entity.UserEntity;
import org.modelmapper.ModelMapper;

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
}
