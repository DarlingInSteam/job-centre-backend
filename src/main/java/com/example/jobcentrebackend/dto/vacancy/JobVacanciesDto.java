package com.example.jobcentrebackend.dto.vacancy;

import com.example.jobcentrebackend.entity.vacancy.JobVacancyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobVacanciesDto {
    private long id;
    private String jobType;
    private String jobTitle;
    private int salary;
    private boolean archived;

    public static JobVacanciesDto toDto(JobVacancyEntity entity) {
        return JobVacanciesDto
                .builder()
                .id(entity.getId())
                .jobTitle(entity.getJobTitle())
                .jobType(entity.getJobType())
                .salary(entity.getSalary())
                .archived(entity.isArchived())
                .build();
    }
}
