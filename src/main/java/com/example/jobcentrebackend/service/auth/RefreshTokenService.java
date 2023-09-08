package com.example.jobcentrebackend.service.auth;

import com.example.jobcentrebackend.entity.auth.RefreshToken;
import com.example.jobcentrebackend.repository.auth.RefreshTokenRepository;
import com.example.jobcentrebackend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public RefreshToken createRefreshToken(String phone) {
        RefreshToken token = RefreshToken
                .builder()
                .user(userRepository.findByPhone(phone).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(1000 * 60 * 60)) // 1 hour
                .build();
        return refreshTokenRepository.save(token);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + "Refresh token was expired. Please make a new signIn request");
        }
        return token;
    }
}

