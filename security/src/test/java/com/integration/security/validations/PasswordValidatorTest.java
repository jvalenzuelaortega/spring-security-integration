package com.integration.security.validations;

import com.integration.security.configuration.RegexPropertiesConfig;
import com.integration.security.exceptions.BadFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class PasswordValidatorTest {

    private PasswordValidator passwordValidator;
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";

    @BeforeEach
    void setUp() {
        RegexPropertiesConfig regexPropertiesConfig = Mockito.mock(RegexPropertiesConfig.class);
        passwordValidator = new PasswordValidator(regexPropertiesConfig);
        when(regexPropertiesConfig.getPassword()).thenReturn(PASSWORD_REGEX);
    }

    @DisplayName("Password is valid when it matches the regex")
    @Test
    void isValid_whenPasswordMatchesRegex_theReturnsTrue() {
        // Act
        String validPassword = "Password1";

        // Assert
        assertTrue(passwordValidator.isValid(validPassword, null));
    }

    @DisplayName("Password is invalid when it does not match the regex")
    @Test
    void isValid_whenPasswordDoesNotMatchRegex_thenReturnBadFormatException() {
        // Act
        String invalidPassword = "password";

        // Assert
        assertThrows(BadFormatException.class, () -> passwordValidator.isValid(invalidPassword, null));
    }

    @DisplayName("Password is invalid when it is null")
    @Test
    void isValid_whenPasswordIsNull_thenReturnBadFormatException() {
        // Assert
        assertThrows(BadFormatException.class, () -> passwordValidator.isValid(null, null));
    }

}