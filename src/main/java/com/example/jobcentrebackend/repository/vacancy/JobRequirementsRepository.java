package com.example.jobcentrebackend.repository.vacancy;

import com.example.jobcentrebackend.entity.vacancy.JobRequirementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRequirementsRepository extends JpaRepository<JobRequirementEntity, Long> {
    Optional<JobRequirementEntity> findById(long id);
}
