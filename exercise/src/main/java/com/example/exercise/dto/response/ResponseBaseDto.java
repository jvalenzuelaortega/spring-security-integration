package com.example.exercise.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBaseDto {

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("created")
    private LocalDateTime created;

    @JsonProperty("modified")
    private LocalDateTime modified;

    @JsonProperty("last_login")
    private LocalDateTime lastLogin;

    @JsonProperty("token")
    private String token;

    @JsonProperty("is_active")
    private boolean isActive;
}
