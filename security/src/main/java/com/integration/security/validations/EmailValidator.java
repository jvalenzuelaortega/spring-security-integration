package com.integration.security.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import com.integration.security.annotations.ValidEmail;
import com.integration.security.configuration.RegexPropertiesConfig;
import com.integration.security.exceptions.BadFormatException;

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
        throw new BadFormatException("Email does not match the required format. Example: email@domail.com");
    }
}
