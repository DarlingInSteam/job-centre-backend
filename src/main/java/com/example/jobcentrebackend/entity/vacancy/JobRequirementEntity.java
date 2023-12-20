package com.example.jobcentrebackend.entity.vacancy;

import com.example.jobcentrebackend.enums.EducationLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

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
    @Enumerated(STRING)
    private EducationLevel educationLevel;

    @Column(name = "age_range_upper")
    private int ageRangeUpper;

    @Column(name = "age_range_lower")
    private int ageRangeLower;

    @Column(name = "work_experience")
    private int workExperience;
}
