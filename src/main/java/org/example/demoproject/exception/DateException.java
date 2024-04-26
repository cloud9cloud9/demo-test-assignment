package org.example.demoproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serial;

public class DateException extends ResponseStatusException {

    @Serial
    private static final long serialVersionUID = 11111L;

    private static final String DEFAULT_MESSAGE = "exception message: date “From” to be less than “To”";

    public DateException() {
        super(HttpStatus.CONFLICT, DEFAULT_MESSAGE);
    }
}
