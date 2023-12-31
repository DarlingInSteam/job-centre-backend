package com.example.jobcentrebackend.service.vacancy;

import com.example.jobcentrebackend.dto.unemployed.UnemployedDto;
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
import com.example.jobcentrebackend.exception.vacancy.JobRequirementsNotFound;
import com.example.jobcentrebackend.exception.vacancy.JobVacacyNotFoundException;
import com.example.jobcentrebackend.exception.vacancy.VacancyAlreadyExists;
import com.example.jobcentrebackend.repository.unemployed.UnemployedRepository;
import com.example.jobcentrebackend.repository.vacancy.JobRequirementsRepository;
import com.example.jobcentrebackend.repository.vacancy.JobVacanciesRepository;
import com.example.jobcentrebackend.repository.employer.EmployerRepository;
import com.example.jobcentrebackend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
                .ageRangeUpper(request.getCreateJobRequirementsRequest().getAgeRangeUpper())
                .ageRangeLower(request.getCreateJobRequirementsRequest().getAgeRangeLower())
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

    public GetJobVacancyResponse getJobVacancy(Long job_id) throws JobVacacyNotFoundException {
        JobVacancyEntity jobVacancyEntity = repository
                .findById(job_id)
                    .orElseThrow(() -> new JobVacacyNotFoundException("Job vacancy not found"));
        JobRequirementEntity jobRequirementEntity = jobRequirementsRepository
                .findById(repository.findById(job_id).get().getRequirementsId())
                    .orElseThrow(() -> new JobVacacyNotFoundException("Job vacancy not found"));

        return GetJobVacancyResponse.builder()
                .jobVacancy(JobVacanciesDto.toDto(jobVacancyEntity))
                .jobRequirement(JobRequirementsDto.toDto(jobRequirementEntity))
                .build();

    }

//    public List<UnemployedDto> getAllUnemployed() {
//        var unemployedList = unemployedRepository.findAll();
//        List<UnemployedDto> unemployedDtoList = new ArrayList<>();
//
//        for (var unemployed: unemployedList) {
//            unemployedDtoList.add(UnemployedDto.toDto(unemployed));
//        }
//
//        return unemployedDtoList;
//    }

    public List<GetJobVacancyResponse> getAllVacancy() throws JobRequirementsNotFound {
        var vacancyList = repository.findAll();
        List<JobVacanciesDto> jobVacanciesDto = new ArrayList<>();
        List<JobRequirementsDto> jobRequirementsDto = new ArrayList<>();
        List<GetJobVacancyResponse> getJobVacancyResponse = new ArrayList<>();

        for (var vacancy: vacancyList) {
            var a = JobVacanciesDto.toDto(vacancy);
            var b = JobRequirementsDto.toDto(
                    jobRequirementsRepository.findById(vacancy.getRequirementsId())
                            .orElseThrow(() -> new JobRequirementsNotFound("Job Requirements Not Found")));
            getJobVacancyResponse.add(new GetJobVacancyResponse(a, b));
        }

        return getJobVacancyResponse;
    }

    public String ApplyVacancyUnemployed(long vacancyId, String username) throws JobVacacyNotFoundException, UserNotFoundException, UnemployedNotFoundException {
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

        return "Unemployed was successfully apply";
    }

    public String archivedVacancy(long vacancyId) throws JobVacacyNotFoundException {
        JobVacancyEntity jobVacancyEntity = repository
                .findById(vacancyId)
                    .orElseThrow(() -> new JobVacacyNotFoundException("Job vacancy not found"));

        jobVacancyEntity.setArchived(true);

        repository.save(jobVacancyEntity);

        return "Job vacancy was successfully archived";
    }

    public String updateVacancy(CreateJobVacancyRequest request, long id) throws JobVacacyNotFoundException, JobRequirementsNotFound {
        JobVacancyEntity jobVacancyEntity = repository
                .findById(id)
                    .orElseThrow(() -> new JobVacacyNotFoundException("Job vacancy not found"));

        jobVacancyEntity.setJobTitle(request.getJobTitle());
        jobVacancyEntity.setJobType(request.getJobType());
        jobVacancyEntity.setSalary(request.getSalary());

        JobRequirementEntity jobRequirementEntity = jobRequirementsRepository
                .findById(jobVacancyEntity.getRequirementsId())
                    .orElseThrow(() -> new JobRequirementsNotFound("Job Requirements not found"));

        jobRequirementEntity.setAgeRangeUpper(request.getCreateJobRequirementsRequest().getAgeRangeUpper());
        jobRequirementEntity.setAgeRangeLower(request.getCreateJobRequirementsRequest().getAgeRangeLower());
        jobRequirementEntity.setEducationLevel(request.getCreateJobRequirementsRequest().getEducationLevel());
        jobRequirementEntity.setWorkExperience(request.getCreateJobRequirementsRequest().getWorkExperience());

        jobRequirementsRepository.save(jobRequirementEntity);
        repository.save(jobVacancyEntity);

        return "Job vacancy was successfully updated";
    }
}
