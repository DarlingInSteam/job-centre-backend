package com.example.jobcentrebackend.dto;

import com.example.jobcentrebackend.enums.EducationLevel;

public class JobRequirementsDto {
    private long id;
    private EducationLevel educationLevel;
    private int[] ageRange;
    private int WorkExperience;
    private String jobType;
    private int jobVacancyId;
}
