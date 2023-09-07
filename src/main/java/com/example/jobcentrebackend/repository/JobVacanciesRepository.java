package com.example.jobcentrebackend.repository;

import com.example.jobcentrebackend.entity.JobVacancyEntity;
import com.example.jobcentrebackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobVacanciesRepository extends JpaRepository<JobVacancyEntity, Long> {
    Optional<JobVacancyEntity> findByJobTitle(String title);
}
