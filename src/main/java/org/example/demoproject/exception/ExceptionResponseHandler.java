package org.example.demoproject.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.demoproject.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponseDto<String>> responseStatusExceptionHandler(final ResponseStatusException exception) {
        log.error(exception.getMessage());
        final var exceptionResponse = new ExceptionResponseDto<String>();
        exceptionResponse.setStatus(exception.getStatusCode().toString());
        exceptionResponse.setDescription(exception.getReason());
        return ResponseEntity.status(exception.getStatusCode()).body(exceptionResponse);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> serverExceptionHandler(final Exception exception) {
        log.error(exception.getMessage());
        final var exceptionResponse = new ExceptionResponseDto<String>();
        exceptionResponse.setStatus(HttpStatus.NOT_IMPLEMENTED.toString());
        exceptionResponse.setDescription("Something went wrong.");
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(exceptionResponse);
    }
}
