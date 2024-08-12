package com.example.exercise.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDetailPhoneDto {

    @JsonProperty("number")
    private String number;

    @JsonProperty("city_code")
    private String citycode;

    @JsonProperty("country_code")
    private String contrycode;
}
