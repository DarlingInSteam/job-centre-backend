package com.example.jobcentrebackend.dto.vacancy;

import com.example.jobcentrebackend.entity.vacancy.JobRequirementEntity;
import com.example.jobcentrebackend.enums.EducationLevel;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobRequirementsDto {
    private long id;
    @Enumerated(STRING)
    private EducationLevel educationLevel;
    private int ageRangeUpper;
    private int ageRangeLower;
    private int workExperience;

    public static JobRequirementsDto toDto(JobRequirementEntity entity) {
        return JobRequirementsDto
                .builder()
                .id(entity.getId())
                .ageRangeUpper(entity.getAgeRangeUpper())
                .ageRangeLower(entity.getAgeRangeLower())
                .educationLevel(entity.getEducationLevel())
                .workExperience(entity.getWorkExperience())
                .build();
    }
}
