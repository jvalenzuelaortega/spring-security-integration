package com.example.exercise.validations;

import com.example.exercise.anontations.ValidEmail;
import com.example.exercise.configuration.RegexPropertiesConfig;
import com.example.exercise.exceptions.BadFormatException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private final RegexPropertiesConfig regexPropertiesConfig;

    public EmailValidator(RegexPropertiesConfig regexPropertiesConfig) {
        this.regexPropertiesConfig = regexPropertiesConfig;
    }

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        String emailRegex = regexPropertiesConfig.getEmail();
        if (email != null && Pattern.matches(emailRegex, email)) {
            return true;
        }
        throw new BadFormatException("Email does not match the required format");
    }
}
