package com.example.jobcentrebackend.dto.vacancy;

import com.example.jobcentrebackend.entity.vacancy.JobRequirementEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetJobVacancyResponse {
    private JobVacanciesDto jobVacancy;
    private JobRequirementsDto jobRequirement;
}
