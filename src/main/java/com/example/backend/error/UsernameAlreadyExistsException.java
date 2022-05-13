package com.example.backend.error;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String s) {
        super(s);
    }
}
