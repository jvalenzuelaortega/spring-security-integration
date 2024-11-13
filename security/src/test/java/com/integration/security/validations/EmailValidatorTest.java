package com.integration.security.validations;

import com.integration.security.configuration.RegexPropertiesConfig;
import com.integration.security.exceptions.BadFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class EmailValidatorTest {

    private EmailValidator emailValidator;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    @BeforeEach
    void setUp() {
        RegexPropertiesConfig regexPropertiesConfig = Mockito.mock(RegexPropertiesConfig.class);
        emailValidator = new EmailValidator(regexPropertiesConfig);

        when(regexPropertiesConfig.getEmail()).thenReturn(EMAIL_REGEX);
    }

    @Test
    void isValid_ReturnsTrue_WhenEmailMatchesRegex() {
        // Act
        String validEmail = "test@example.com";

        // Assert
        assertTrue(emailValidator.isValid(validEmail, null));
    }

    @Test
    void isValid_ThrowsBadFormatException_WhenEmailDoesNotMatchRegex() {
        // Act
        String invalidEmail = "invalid-email";

        // Assert
        assertThrows(BadFormatException.class, () -> emailValidator.isValid(invalidEmail, null));
    }

    @Test
    void isValid_ThrowsBadFormatException_WhenEmailIsNull() {
        // Assert
        assertThrows(BadFormatException.class, () -> emailValidator.isValid(null, null));
    }
}