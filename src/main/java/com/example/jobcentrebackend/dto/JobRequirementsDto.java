package com.example.jobcentrebackend.dto;

import com.example.jobcentrebackend.entity.JobRequirementEntity;
import com.example.jobcentrebackend.enums.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobRequirementsDto {
    private long id;
    private EducationLevel educationLevel;
    private int[] ageRange;
    private int workExperience;

    public static JobRequirementsDto toDto(JobRequirementEntity entity) {
        return JobRequirementsDto
                .builder()
                .id(entity.getId())
                .ageRange(entity.getAgeRange())
                .educationLevel(entity.getEducationLevel())
                .workExperience(entity.getWorkExperience())
                .build();
    }
}
