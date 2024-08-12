package com.example.exercise.validations;

import com.example.exercise.configuration.RegexPropertiesConfig;
import com.example.exercise.exceptions.BadFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorTest {

    private EmailValidator emailValidator;
    private RegexPropertiesConfig regexPropertiesConfig;

    @BeforeEach
    void setUp() {
        regexPropertiesConfig = Mockito.mock(RegexPropertiesConfig.class);
        emailValidator = new EmailValidator(regexPropertiesConfig);
    }

    @Test
    void isValid_ReturnsTrue_WhenEmailMatchesRegex() {
        Mockito.when(regexPropertiesConfig.getEmail()).thenReturn("^[A-Za-z0-9+_.-]+@(.+)$");
        String validEmail = "test@example.com";

        assertTrue(emailValidator.isValid(validEmail, null));
    }

    @Test
    void isValid_ThrowsBadFormatException_WhenEmailDoesNotMatchRegex() {
        Mockito.when(regexPropertiesConfig.getEmail()).thenReturn("^[A-Za-z0-9+_.-]+@(.+)$");
        String invalidEmail = "invalid-email";

        assertThrows(BadFormatException.class, () -> emailValidator.isValid(invalidEmail, null));
    }

    @Test
    void isValid_ThrowsBadFormatException_WhenEmailIsNull() {
        Mockito.when(regexPropertiesConfig.getEmail()).thenReturn("^[A-Za-z0-9+_.-]+@(.+)$");

        assertThrows(BadFormatException.class, () -> emailValidator.isValid(null, null));
    }
}