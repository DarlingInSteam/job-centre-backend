package com.example.jobcentrebackend.repository;

import com.example.jobcentrebackend.entity.EmploymentHistoryEntity;
import com.example.jobcentrebackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistoryEntity, Long> {
    Optional<EmploymentHistoryEntity> findByEmploymentDate(Date employmentDate);
}