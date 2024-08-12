package com.example.exercise.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class ResponseDetailsDto extends ResponseBaseDto {

    @JsonProperty("password")
    private String password;

    @JsonProperty("phones")
    private List<ResponseDetailPhoneDto> phones;
}
