package com.example.jobcentrebackend.service.auth;

import com.example.jobcentrebackend.dto.auth.AuthenticationRequest;
import com.example.jobcentrebackend.dto.auth.AuthenticationResponse;
import com.example.jobcentrebackend.dto.auth.RegisterRequest;
import com.example.jobcentrebackend.entity.auth.PasswordResetTokenEntity;
import com.example.jobcentrebackend.entity.auth.RefreshToken;
import com.example.jobcentrebackend.entity.user.UserEntity;
import com.example.jobcentrebackend.exception.auth.PasswordResetTokenNotFoundException;
import com.example.jobcentrebackend.exception.auth.UsernameIsOccupiedException;
import com.example.jobcentrebackend.exception.user.UserNotFoundException;
import com.example.jobcentrebackend.repository.auth.PasswordResetTokenRepository;
import com.example.jobcentrebackend.repository.user.UserRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final String apiUrl = "https://localhost:8080";

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public AuthenticationResponse register(RegisterRequest request) throws UsernameIsOccupiedException, UserNotFoundException {
        validateRegisterRequest(request);

        var user = UserEntity.builder()
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        repository.save(user);

        String jwtToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());

        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .token(refreshToken.getToken())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws UserNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhone(),
                        request.getPassword()
                )
        );

        var user = repository.findByPhone(request.getPhone()).orElseThrow(() -> new UserNotFoundException("User not found"));

        String jwtToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());

        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .token(refreshToken.getToken())
                .build();
    }

    public String resetPassword(String password, String token) throws PasswordResetTokenNotFoundException {
        PasswordResetTokenEntity tokenEntity = passwordResetTokenRepository.findByToken(token).orElseThrow(() -> new PasswordResetTokenNotFoundException("Password reset token not found"));
        UserEntity userEntity = tokenEntity.getUser();

        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setPasswordResetToken(null);
        repository.save(userEntity);

        passwordResetTokenRepository.delete(tokenEntity);

        return "Password reset succeeded";
    }


    private void validateRegisterRequest(RegisterRequest request) throws UsernameIsOccupiedException {
        if (repository.findByPhone(request.getPhone()).isPresent())
            throw new UsernameIsOccupiedException("Account with this username is already registered");
    }
}
