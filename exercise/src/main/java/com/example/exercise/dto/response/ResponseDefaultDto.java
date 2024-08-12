package com.example.exercise.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class ResponseDefaultDto extends ResponseBaseDto {

    @JsonProperty("id")
    private UUID id;
}
