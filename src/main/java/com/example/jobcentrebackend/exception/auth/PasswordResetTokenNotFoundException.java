package com.example.jobcentrebackend.exception.auth;

public class PasswordResetTokenNotFoundException extends Exception {
    public PasswordResetTokenNotFoundException(String message) {
        super(message);
    }
}
