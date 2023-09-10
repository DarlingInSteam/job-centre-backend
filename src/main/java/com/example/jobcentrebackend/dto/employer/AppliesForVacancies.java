package com.example.jobcentrebackend.dto.employer;

import com.example.jobcentrebackend.dto.unemployed.UnemployedDto;
import com.example.jobcentrebackend.dto.vacancy.JobRequirementsDto;
import com.example.jobcentrebackend.dto.vacancy.JobVacanciesDto;
import com.example.jobcentrebackend.entity.unemployed.UnemployedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppliesForVacancies {
    private JobVacanciesDto jobVacanciesDto;
    private JobRequirementsDto jobRequirementsDto;
    private List<UnemployedDto> unemployedDto;
}
