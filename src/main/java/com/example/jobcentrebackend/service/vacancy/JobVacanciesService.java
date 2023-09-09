package com.example.jobcentrebackend.service.vacancy;

import com.example.jobcentrebackend.dto.vacancy.CreateJobVacancyRequest;
import com.example.jobcentrebackend.dto.vacancy.GetJobVacancyResponse;
import com.example.jobcentrebackend.dto.vacancy.JobRequirementsDto;
import com.example.jobcentrebackend.dto.vacancy.JobVacanciesDto;
import com.example.jobcentrebackend.entity.employer.EmployerEntity;
import com.example.jobcentrebackend.entity.vacancy.JobRequirementEntity;
import com.example.jobcentrebackend.entity.vacancy.JobVacancyEntity;
import com.example.jobcentrebackend.exception.employer.EmployerNotFoundException;
import com.example.jobcentrebackend.exception.user.UserNotFoundException;
import com.example.jobcentrebackend.exception.vacancy.JobVacacyNotFoundException;
import com.example.jobcentrebackend.exception.vacancy.VacancyAlreadyExists;
import com.example.jobcentrebackend.repository.JobRequirementsRepository;
import com.example.jobcentrebackend.repository.JobVacanciesRepository;
import com.example.jobcentrebackend.repository.employer.EmployerRepository;
import com.example.jobcentrebackend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobVacanciesService {
    @Autowired
    private JobVacanciesRepository repository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRequirementsRepository jobRequirementsRepository;

    public String createVacancy(CreateJobVacancyRequest request) throws VacancyAlreadyExists, UserNotFoundException, EmployerNotFoundException, JobVacacyNotFoundException {
        if (repository.findByJobTitle(request.getJobTitle()).isPresent()) {
            throw new VacancyAlreadyExists("Vacancy already exists");
        }

        EmployerEntity employer = employerRepository.findByUser(userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found")))
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found"));

        JobRequirementEntity requirementEntity =  jobRequirementsRepository.save(JobRequirementEntity
                .builder()
                .educationLevel(request.getCreateJobRequirementsRequest().getEducationLevel())
                .ageRange(request.getCreateJobRequirementsRequest().getAgeRange())
                .workExperience(request.getCreateJobRequirementsRequest().getWorkExperience())
                .build()
        );

        long requirementId = requirementEntity.getId();

        JobVacancyEntity vacancy = repository.save(JobVacancyEntity
                .builder()
                .employerEntity(employer)
                .jobType(request.getJobType())
                .jobTitle(request.getJobTitle())
                .archived(false)
                .salary(request.getSalary())
                .requirementsId(requirementId)
                .build()
        );

        employer.getJobVacancyEntity().add(vacancy);
        employerRepository.save(employer);

        return "Vacancy was successfully created";
    }

    public GetJobVacancyResponse getJobVacancy(String jobTitle) throws JobVacacyNotFoundException {
        JobVacancyEntity jobVacancyEntity = repository.findByJobTitle(jobTitle)
                .orElseThrow(() -> new JobVacacyNotFoundException("Job vacancy not found"));
        JobRequirementEntity jobRequirementEntity = jobRequirementsRepository.findById(
                        repository.findByJobTitle(jobTitle).get().getRequirementsId())
                .orElseThrow(() -> new JobVacacyNotFoundException("Job vacancy not found"));

        return GetJobVacancyResponse.builder()
                .jobVacancy(JobVacanciesDto.toDto(jobVacancyEntity))
                .jobRequirement(JobRequirementsDto.toDto(jobRequirementEntity))
                .build();

    }
}
