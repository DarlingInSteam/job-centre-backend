package com.example.jobcentrebackend.repository;

import com.example.jobcentrebackend.entity.JobRequirementEntity;

import java.util.Optional;

public interface JobRequirementsRepository {
    Optional<JobRequirementEntity> findByJobVacancyId(int id);
}
