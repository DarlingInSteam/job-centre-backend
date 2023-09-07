package com.example.jobcentrebackend.repository;

import com.example.jobcentrebackend.entity.UnemployedEntity;
import com.example.jobcentrebackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnemployedRepository extends JpaRepository<UnemployedEntity, Long> {
    Optional<UnemployedEntity> findByPassportNumber(String number);
}
