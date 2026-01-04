package com.flightontime.api.infra.exception;

import com.flightontime.api.infra.ValidatorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    // Global handler
    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<ValidationError> handleValidationExceptions(ValidatorException ex) {
        var error = new ValidationError(ex.getField(), ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> error404(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> error500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERRO: Ocorreu um erro inesperado");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ValidationError> jsonError(HttpMessageNotReadableException ex) {
        var erro = new ValidationError("json", "O corpo da requisição está malformado ou com erro de sintaxe");
        return ResponseEntity.badRequest().body(erro);
    }

    private record ValidationError(String field, String message) {
        public ValidationError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
