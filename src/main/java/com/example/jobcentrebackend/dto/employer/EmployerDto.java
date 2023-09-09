package com.example.jobcentrebackend.dto.employer;

import com.example.jobcentrebackend.dto.vacancy.JobVacanciesDto;
import com.example.jobcentrebackend.entity.empoyer.EmployerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployerDto {
    private long id;
    private String employerName;
    private String address;
    private List<JobVacanciesDto> vacancies;

    public static EmployerDto toDto(EmployerEntity entity) {
        return EmployerDto
                .builder()
                .id(entity.getId())
                .employerName(entity.getEmployerName())
                .address(entity.getAddress())
                .vacancies(entity.getJobVacancies()
                        .stream()
                        .map(JobVacanciesDto::toDto)
                        .toList()
                )
                .build();
    }
}
