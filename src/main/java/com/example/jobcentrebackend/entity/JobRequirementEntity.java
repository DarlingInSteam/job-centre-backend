package com.example.jobcentrebackend.entity;

import com.example.jobcentrebackend.enums.EducationLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="job_requirements")
public class JobRequirementEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "education_level")
    private EducationLevel educationLevel;

    @Column(name = "age_range")
    private int[] ageRange;

    @Column(name = "work_experience")
    private int workExperience;

    @OneToOne
    @JoinColumn(name = "job_vacancy_id")
    private JobVacancyEntity jobVacancy;
}
