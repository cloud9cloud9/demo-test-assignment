package org.example.demoproject.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateUtil {

    @Value("${app.min-age}")
    private int MIN_AGE;

    public boolean isAdult(LocalDate birthDate) {
        LocalDate minDate = LocalDate.now().minusYears(MIN_AGE);
        return !birthDate.isAfter(minDate);
    }

    public boolean isDateBefore(LocalDate date, LocalDate referenceDate) {
        return date.isBefore(referenceDate);
    }
}
