package com.example.jobcentrebackend.dto.vacancy;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobVacancyRequest {
    private String jobType;
    private String jobTitle;
    private int salary;
    private String username;
    private CreateJobRequirementsRequest createJobRequirementsRequest;
}
