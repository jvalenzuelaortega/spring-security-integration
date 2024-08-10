package com.example.exercise.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegexConfig {

    @Value("${email.regular.expression}")
    private String myFieldRegex;

    public String getMyFieldRegex() {
        return myFieldRegex;
    }
}
