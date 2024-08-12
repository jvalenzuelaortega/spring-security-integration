package com.example.exercise.validations;

import com.example.exercise.configuration.RegexPropertiesConfig;
import com.example.exercise.exceptions.BadFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {

    private PasswordValidator passwordValidator;
    private RegexPropertiesConfig regexPropertiesConfig;

    @BeforeEach
    void setUp() {
        regexPropertiesConfig = Mockito.mock(RegexPropertiesConfig.class);
        passwordValidator = new PasswordValidator(regexPropertiesConfig);
    }

    @Test
    void isValid_ReturnsTrue_WhenPasswordMatchesRegex() {
        Mockito.when(regexPropertiesConfig.getPassword()).thenReturn("^(?=.*[0-9])(?=.*[A-Z]).{8,}$");
        String validPassword = "Password1";

        assertTrue(passwordValidator.isValid(validPassword, null));
    }

    @Test
    void isValid_ThrowsBadFormatException_WhenPasswordDoesNotMatchRegex() {
        Mockito.when(regexPropertiesConfig.getPassword()).thenReturn("^(?=.*[0-9])(?=.*[A-Z]).{8,}$");
        String invalidPassword = "password";

        assertThrows(BadFormatException.class, () -> passwordValidator.isValid(invalidPassword, null));
    }

    @Test
    void isValid_ThrowsBadFormatException_WhenPasswordIsNull() {
        Mockito.when(regexPropertiesConfig.getPassword()).thenReturn("^(?=.*[0-9])(?=.*[A-Z]).{8,}$");

        assertThrows(BadFormatException.class, () -> passwordValidator.isValid(null, null));
    }

}