package com.reportplus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return errors;
    }

    // Handle runtime errors (fallback for service exceptions)
    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handleRuntimeException(RuntimeException ex) {

        Map<String, Object> error = new HashMap<>();

        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", ex.getMessage());

        return error;
    }

    // Handle all other unexpected errors
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleGenericException(Exception ex) {

        Map<String, Object> error = new HashMap<>();

        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Something went wrong");
        error.put("details", ex.getMessage());

        return error;
    }
}