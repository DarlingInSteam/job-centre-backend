package com.example.jobcentrebackend.repository;

import com.example.jobcentrebackend.entity.EmployerEntity;
import com.example.jobcentrebackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<EmployerEntity, Long> {
    Optional<EmployerEntity> findByEmployerName(String name);
}
