package com.example.jobcentrebackend.repository;

import com.example.jobcentrebackend.entity.vacancy.JobRequirementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRequirementsRepository extends JpaRepository<JobRequirementEntity, Long> {
    Optional<JobRequirementEntity> findByJobVacancyId(long id);
}
