package org.example.demoproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serial;

public class UserAgeException extends ResponseStatusException {

    @Serial
    private static final long serialVersionUID = 11111L;

    private static final String DEFAULT_MESSAGE = "user must be at least 18 years old";

    public UserAgeException() {
        super(HttpStatus.CONFLICT, DEFAULT_MESSAGE);
    }
}
