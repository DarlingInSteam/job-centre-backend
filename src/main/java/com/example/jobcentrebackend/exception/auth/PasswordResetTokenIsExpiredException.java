package com.example.jobcentrebackend.exception.auth;

public class PasswordResetTokenIsExpiredException extends Exception{
    public PasswordResetTokenIsExpiredException(String message) {
        super(message);
    }
}
