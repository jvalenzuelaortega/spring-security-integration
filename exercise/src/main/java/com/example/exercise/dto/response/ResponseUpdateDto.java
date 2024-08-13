package com.example.exercise.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ResponseUpdateDto extends ResponseBaseDto {

    @JsonProperty("password")
    private String password;

    @JsonIgnore
    private boolean isActive;
}
