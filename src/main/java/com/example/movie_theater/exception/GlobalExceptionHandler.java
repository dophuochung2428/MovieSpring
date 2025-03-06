package com.example.movie_theater.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateHallNameException.class)
    public ResponseEntity<String> handleDuplicateHallNameException(DuplicateHallNameException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
