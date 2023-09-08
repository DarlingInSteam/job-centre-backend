package com.example.jobcentrebackend.repository.user;

import com.example.jobcentrebackend.entity.user.EmployerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<EmployerEntity, Long> {
    Optional<EmployerEntity> findByEmployerName(String name);
}
