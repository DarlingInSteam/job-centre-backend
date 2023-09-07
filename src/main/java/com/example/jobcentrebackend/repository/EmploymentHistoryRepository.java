package com.example.jobcentrebackend.repository;

import com.example.jobcentrebackend.entity.EmploymentHistoryEntity;

import java.util.Date;
import java.util.Optional;

public interface EmploymentHistoryRepository {
    Optional<EmploymentHistoryEntity> findByEmploymentDate(Date employmentDate);
}
