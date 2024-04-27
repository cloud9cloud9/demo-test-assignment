package org.example.demoproject.service;

import lombok.RequiredArgsConstructor;
import org.example.demoproject.exception.DateException;
import org.example.demoproject.exception.InvalidUserAgeException;
import org.example.demoproject.util.DateUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final DateUtil dateUtil;

    public boolean validateUserAge(LocalDate birthDate) {
        final var isAdult = dateUtil.isAdult(birthDate);
        if (!isAdult) {
            throw new InvalidUserAgeException();
        }
        return true;
    }

    public boolean validateUserAgeBetween(LocalDate from, LocalDate to) {
        final var isAdult = dateUtil.isDateBefore(from, to);
        if (!isAdult) {
            throw new DateException();
        }
        return true;
    }
}
