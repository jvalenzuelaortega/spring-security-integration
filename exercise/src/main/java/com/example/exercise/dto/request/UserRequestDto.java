package com.example.exercise.dto.request;

import com.example.exercise.anontations.ValidEmail;
import com.example.exercise.anontations.ValidPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserRequestDto {

    @JsonProperty("name")
    @NotBlank(message = "name is required")
    public String name;

    @NotBlank
    @ValidEmail
    @JsonProperty("email")
    public String email;

    @NotBlank
    @ValidPassword
    @JsonProperty("password")
    @Size(max = 8, message = "password must be 8 characters")
    public String password;

    @JsonProperty("phones")
    public List<PhoneRequestDto> phones;

}
