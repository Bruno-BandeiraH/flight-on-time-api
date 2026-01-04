package com.flightontime.api.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<String> handleValidationExceptions(ValidatorException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
