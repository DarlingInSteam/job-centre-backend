package com.example.jobcentrebackend.controller;

import com.example.jobcentrebackend.dto.auth.AuthenticationRequest;
import com.example.jobcentrebackend.dto.auth.AuthenticationResponse;
import com.example.jobcentrebackend.dto.auth.RefreshTokenRequest;
import com.example.jobcentrebackend.dto.auth.RegisterRequest;
import com.example.jobcentrebackend.exception.auth.RefreshTokenNotFoundException;
import com.example.jobcentrebackend.exception.user.UserNotFoundException;
import com.example.jobcentrebackend.service.auth.AuthenticationService;
import com.example.jobcentrebackend.service.auth.JwtService;
import com.example.jobcentrebackend.service.auth.RefreshTokenService;
import com.example.jobcentrebackend.entity.auth.RefreshToken;
import com.example.jobcentrebackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/validate_pass_token")
    public ResponseEntity validatePasswordResetToken(@RequestParam String token) {
        try {
            return ResponseEntity.ok(service.validatePasswordResetToken(token));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reset_password")
    public ResponseEntity resetPassword(@RequestParam String password, @RequestParam String token) {
        try {
            return ResponseEntity.ok(service.resetPassword(password, token));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws RefreshTokenNotFoundException, UserNotFoundException {
        String refreshToken = refreshTokenRequest.getToken();
        var userRole = userService.getUserUsername(refreshTokenRequest.getUsername());

        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.generateToken(user);
                    return AuthenticationResponse
                            .builder()
                            .accessToken(accessToken)
                            .token(refreshToken)
                            .username(refreshTokenRequest.getUsername())
                            .role(userRole.getRole())
                            .build();
                }).orElseThrow(() -> new RefreshTokenNotFoundException("Refresh token not found"));
    }
}
