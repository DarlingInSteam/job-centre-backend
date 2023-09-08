package com.example.jobcentrebackend.exception.auth;

public class RefreshTokenNotFoundException extends Exception{
    public RefreshTokenNotFoundException(String message) {
        super(message);
    }
}
