package com.example.jobcentrebackend.repository;

import com.example.jobcentrebackend.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findByPhoneNumber(String phone);
}
