package com.example.backend.error;

public class UserPasswordIncorrectException extends RuntimeException {
    public UserPasswordIncorrectException(String s) {
        super(s);
    }
}
