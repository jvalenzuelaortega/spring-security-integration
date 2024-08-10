package com.example.exercise.dto.request;

import com.example.exercise.annotations.ValidRegex;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserRequestDto {

    @JsonProperty("name")
    public String name;

    @JsonProperty("email")
    @ValidRegex
    public String email;

    @JsonProperty("password")
    public String password;

    @JsonProperty("phones")
    public List<PhoneRequestDto> phones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneRequestDto> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneRequestDto> phones) {
        this.phones = phones;
    }
}
