package com.integration.security.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @DisplayName("Get local date time with valid instant returns formatted date time")
    @Test
    void getLocalDateTime_withValidInstant_returnsFormattedDateTime() {
        Instant instant = Instant.parse("2024-08-01T12:00:00.00Z");
        String expectedDateTime = "2024-08-01 08:00:00";

        String actualDateTime = DateUtils.getLocalDateTime(instant);

        assertEquals(expectedDateTime, actualDateTime);
    }

}