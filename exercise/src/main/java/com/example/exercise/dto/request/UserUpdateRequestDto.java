package com.example.exercise.dto.request;

import com.example.exercise.anontations.ValidEmail;
import com.example.exercise.anontations.ValidPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateRequestDto {

    private String name;

    @NotBlank
    @ValidEmail
    @JsonProperty("email")
    private String email;

    @NotBlank
    @ValidPassword
    @JsonProperty("password")
    @Size(max = 8, message = "password must be 8 characters")
    private String password;
}
