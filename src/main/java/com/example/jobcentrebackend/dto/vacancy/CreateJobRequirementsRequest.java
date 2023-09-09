package com.example.jobcentrebackend.dto.vacancy;

import com.example.jobcentrebackend.enums.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobRequirementsRequest {
    private EducationLevel educationLevel;
    private int[] ageRange;
    private int workExperience;
}
