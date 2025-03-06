package com.example.movie_theater.exception;

public class DuplicateHallNameException extends RuntimeException {
    public DuplicateHallNameException(String message) {
        super(message);
    }
}
