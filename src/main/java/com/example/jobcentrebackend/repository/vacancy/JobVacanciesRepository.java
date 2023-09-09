package com.example.jobcentrebackend.repository.vacancy;

import com.example.jobcentrebackend.entity.vacancy.JobVacancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobVacanciesRepository extends JpaRepository<JobVacancyEntity, Long> {
    Optional<JobVacancyEntity> findByJobTitle(String title);
}
