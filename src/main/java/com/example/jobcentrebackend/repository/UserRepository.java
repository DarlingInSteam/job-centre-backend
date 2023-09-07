package com.example.jobcentrebackend.repository;

import com.example.jobcentrebackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPhone(String phone);
    Optional<UserEntity> findByUsername(String username);
}
