package com.vm.jdbc.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
@UtilityClass
public class LocalDateFormatter {
    private static final String PATTERN = "yyyy-DD-mm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public   LocalDate format(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
    public boolean isValid(String date){
        return Optional
                .ofNullable(date)
                .map(LocalDateFormatter::format)
                .isPresent();
    }
}
