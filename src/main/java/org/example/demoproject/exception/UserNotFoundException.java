package org.example.demoproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serial;

public class UserNotFoundException extends ResponseStatusException {

    @Serial
    private static final long serialVersionUID = 33424424242L;

    private static final String DEFAULT_MESSAGE = "user not found";

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, DEFAULT_MESSAGE);
    }
}
