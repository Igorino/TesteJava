package com.test.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataTransferenciaInvalidaException.class)
    public ResponseEntity<String> handleDataTransferenciaInvalidaException(DataTransferenciaInvalidaException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Outros métodos de tratamento de exceção podem ser adicionados aqui
}
