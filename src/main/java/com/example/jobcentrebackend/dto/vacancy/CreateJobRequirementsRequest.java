package com.example.jobcentrebackend.dto.vacancy;

import com.example.jobcentrebackend.enums.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobRequirementsRequest {
    private EducationLevel educationLevel;
    private int ageRangeUpper;
    private int ageRangeLower;
    private int workExperience;
}
