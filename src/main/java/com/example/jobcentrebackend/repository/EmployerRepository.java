package com.example.jobcentrebackend.repository;

import com.example.jobcentrebackend.entity.EmployerEntity;

import java.util.Optional;

public interface EmployerRepository {
    Optional<EmployerEntity> findByEmployerName(String name);
}
