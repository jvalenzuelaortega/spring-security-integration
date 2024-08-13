package com.example.exercise.mapper;

import com.example.exercise.dto.response.ResponseDetailPhoneDto;
import com.example.exercise.entity.PhoneEntity;

import java.util.ArrayList;
import java.util.List;

public class PhoneResponseMapper {

    public static List<ResponseDetailPhoneDto> mapperToResponseDetailPhoneDto(List<PhoneEntity> phoneEntities) {
        List<ResponseDetailPhoneDto> responseDetailPhoneDtoList = new ArrayList<>();
        for (PhoneEntity phoneEntity : phoneEntities) {
            responseDetailPhoneDtoList.add(ResponseDetailPhoneDto.builder()
                    .number(phoneEntity.getNumber())
                    .cityCode(phoneEntity.getCityCode())
                    .countryCode(phoneEntity.getCountryCode())
                    .build());
        }
        return responseDetailPhoneDtoList;
    }
}
