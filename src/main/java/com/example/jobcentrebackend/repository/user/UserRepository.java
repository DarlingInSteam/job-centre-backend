package com.example.jobcentrebackend.repository.user;

import com.example.jobcentrebackend.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPhone(String phone);
    Optional<UserEntity> findByUsername(String username);
}
