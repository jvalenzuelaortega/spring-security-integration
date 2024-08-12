package com.example.exercise.validations;

import com.example.exercise.anontations.ValidPassword;
import com.example.exercise.configuration.RegexPropertiesConfig;
import com.example.exercise.exceptions.BadFormatException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private final RegexPropertiesConfig regexPropertiesConfig;

    public PasswordValidator(RegexPropertiesConfig regexPropertiesConfig) {
        this.regexPropertiesConfig = regexPropertiesConfig;
    }

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        String passwordRegex = regexPropertiesConfig.getPassword();
        if (password != null && Pattern.matches(passwordRegex, password)) {
            return true;
        }

        throw new BadFormatException("Password does not match the required format." +
                "Password must be 8 characters long, with the first letter capitalized and a digit");
    }
}
