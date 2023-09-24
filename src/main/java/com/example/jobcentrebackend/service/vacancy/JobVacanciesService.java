package com.example.jobcentrebackend.service.vacancy;

import com.example.jobcentrebackend.dto.vacancy.CreateJobVacancyRequest;
import com.example.jobcentrebackend.dto.vacancy.GetJobVacancyResponse;
import com.example.jobcentrebackend.dto.vacancy.JobRequirementsDto;
import com.example.jobcentrebackend.dto.vacancy.JobVacanciesDto;
import com.example.jobcentrebackend.entity.employer.EmployerEntity;
import com.example.jobcentrebackend.entity.unemployed.UnemployedEntity;
import com.example.jobcentrebackend.entity.vacancy.JobRequirementEntity;
import com.example.jobcentrebackend.entity.vacancy.JobVacancyEntity;
import com.example.jobcentrebackend.exception.employer.EmployerNotFoundException;
import com.example.jobcentrebackend.exception.unemployed.UnemployedNotFoundException;
import com.example.jobcentrebackend.exception.user.UserNotFoundException;
import com.example.jobcentrebackend.exception.vacancy.JobVacacyNotFoundException;
import com.example.jobcentrebackend.exception.vacancy.VacancyAlreadyExists;
import com.example.jobcentrebackend.repository.unemployed.UnemployedRepository;
import com.example.jobcentrebackend.repository.vacancy.JobRequirementsRepository;
import com.example.jobcentrebackend.repository.vacancy.JobVacanciesRepository;
import com.example.jobcentrebackend.repository.employer.EmployerRepository;
import com.example.jobcentrebackend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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

    @Autowired
    private UnemployedRepository unemployedRepository;

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
        JobVacancyEntity jobVacancyEntity = repository
                .findByJobTitle(jobTitle)
                    .orElseThrow(() -> new JobVacacyNotFoundException("Job vacancy not found"));
        JobRequirementEntity jobRequirementEntity = jobRequirementsRepository
                .findById(repository.findByJobTitle(jobTitle).get().getRequirementsId())
                    .orElseThrow(() -> new JobVacacyNotFoundException("Job vacancy not found"));

        return GetJobVacancyResponse.builder()
                .jobVacancy(JobVacanciesDto.toDto(jobVacancyEntity))
                .jobRequirement(JobRequirementsDto.toDto(jobRequirementEntity))
                .build();

    }

    public boolean ApplyVacancyUnemployed(long vacancyId, String username) throws JobVacacyNotFoundException, UserNotFoundException, UnemployedNotFoundException {
        JobVacancyEntity jobVacancyEntity = repository
                .findById(vacancyId)
                    .orElseThrow(() -> new JobVacacyNotFoundException("Job vacancy not found"));
        UnemployedEntity unemployedEntity = unemployedRepository
                .findByUser(userRepository.findByUsername(username)
                        .orElseThrow(() -> new UserNotFoundException("User not found")))
                        .orElseThrow(() -> new UnemployedNotFoundException("Unemployed not found exception"));

        Set<UnemployedEntity> unemployedEntities = jobVacancyEntity.getUnemployedEntities();

        unemployedEntities.add(unemployedEntity);

        jobVacancyEntity.setUnemployedEntities(unemployedEntities);

        repository.save(jobVacancyEntity);

        return true;
    }

    public String archivedVacancy(long vacancyId) throws JobVacacyNotFoundException {
        JobVacancyEntity jobVacancyEntity = repository
                .findById(vacancyId)
                .orElseThrow(() -> new JobVacacyNotFoundException("Job vacancy not found"));

        jobVacancyEntity.setArchived(true);

        repository.save(jobVacancyEntity);

        return "Job vacancy was successfully archived";
    }
}
