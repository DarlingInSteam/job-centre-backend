package com.example.jobcentrebackend.repository;

import com.example.jobcentrebackend.entity.JobVacancyEntity;

import java.util.Optional;

public interface JobVacanciesRepository {
    Optional<JobVacancyEntity> findByJobTitle(String title);
}
