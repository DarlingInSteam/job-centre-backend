package com.example.jobcentrebackend.repository.employer;

import com.example.jobcentrebackend.entity.employer.EmployerEntity;
import com.example.jobcentrebackend.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<EmployerEntity, Long> {
    Optional<EmployerEntity> findByEmployerName(String name);
    Optional<EmployerEntity> findByUser(UserEntity user);
}
