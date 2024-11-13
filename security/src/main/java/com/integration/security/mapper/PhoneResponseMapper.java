package com.integration.security.mapper;

import java.util.ArrayList;
import java.util.List;

import com.integration.security.dto.response.ResponseDetailPhoneDto;
import com.integration.security.entity.PhoneEntity;

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
