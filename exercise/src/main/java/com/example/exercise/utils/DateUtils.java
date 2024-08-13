package com.example.exercise.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getLocalDateTime(Instant instant) {
        if (instant == null) return null;

        return DateTimeFormatter.ofPattern(DATETIME_FORMAT)
                .format(instant.atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

}
