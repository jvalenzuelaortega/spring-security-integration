package com.example.exercise.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneRequestDto {

    @JsonProperty("number")
    @NotBlank(message = "number is required")
    public String number;

    @JsonProperty("citycode")
    @NotBlank(message = "city_code is required")
    public String cityCode;

    @JsonProperty("contrycode")
    @NotBlank(message = "country_code is required")
    public String countryCode;

}
