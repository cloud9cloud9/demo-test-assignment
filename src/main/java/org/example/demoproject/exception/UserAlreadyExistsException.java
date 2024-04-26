package org.example.demoproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serial;

public class UserAlreadyExistsException extends ResponseStatusException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(String badUserCredential) {
        super(HttpStatus.CONFLICT,badUserCredential);
    }
}
