package com.example.exercise.validations;

import com.example.exercise.annotations.ValidRegex;
import com.example.exercise.configuration.RegexConfig;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegexValidator implements ConstraintValidator<ValidRegex, String> {

    private final RegexConfig regexConfig;

    public RegexValidator(RegexConfig regexConfig) {
        this.regexConfig = regexConfig;
    }

    @Override
    public void initialize(ValidRegex constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return value.matches(regexConfig.getMyFieldRegex());
    }
}
