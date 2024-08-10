package com.example.exercise.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneRequestDto {

    @JsonProperty("number")
    public String number;

    @JsonProperty("citycode")
    public String cityCode;

    @JsonProperty("contrycode")
    public String countryCode;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
