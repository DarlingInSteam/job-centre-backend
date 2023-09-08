package com.example.jobcentrebackend.exception.auth;

public class UsernameIsOccupiedException extends Exception {
    public UsernameIsOccupiedException(String message) {
        super(message);
    }
}
