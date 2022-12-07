package com.example.springjwtauth.exception;

import com.example.springjwtauth.domain.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(exception.getMessage()));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> userExceptionHandler(UserException exception) {
        return ResponseEntity.status(exception.getErrorCode().getHttpStatus())
                .body(Response.error(exception.getErrorCode().getMessage()));
    }
}