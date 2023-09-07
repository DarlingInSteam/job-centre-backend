package com.example.jobcentrebackend.repository;

import com.example.jobcentrebackend.entity.UnemployedEntity;

import java.util.Optional;

public interface UnemployedRepository {
    Optional<UnemployedEntity> findByPassportNumber(String number);
}
