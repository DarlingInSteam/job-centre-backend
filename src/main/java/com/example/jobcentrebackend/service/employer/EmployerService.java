package com.example.jobcentrebackend.service.employer;

import com.example.jobcentrebackend.dto.employer.AppliesForVacancies;
import com.example.jobcentrebackend.dto.employer.CreateEmployerRequest;
import com.example.jobcentrebackend.dto.employer.EmployerDto;
import com.example.jobcentrebackend.dto.unemployed.UnemployedDto;
import com.example.jobcentrebackend.dto.vacancy.GetJobVacancyResponse;
import com.example.jobcentrebackend.dto.vacancy.JobRequirementsDto;
import com.example.jobcentrebackend.dto.vacancy.JobVacanciesDto;
import com.example.jobcentrebackend.entity.employer.EmployerEntity;
import com.example.jobcentrebackend.entity.unemployed.UnemployedEntity;
import com.example.jobcentrebackend.entity.vacancy.EmploymentHistoryEntity;
import com.example.jobcentrebackend.entity.vacancy.JobVacancyEntity;
import com.example.jobcentrebackend.enums.Role;
import com.example.jobcentrebackend.exception.employer.EmployerAlreadyExists;
import com.example.jobcentrebackend.exception.employer.EmployerNotFoundException;
import com.example.jobcentrebackend.exception.unemployed.UnemployedNotFoundException;
import com.example.jobcentrebackend.exception.user.UserHasInappropriateRole;
import com.example.jobcentrebackend.exception.user.UserNotFoundException;
import com.example.jobcentrebackend.repository.unemployed.UnemployedRepository;
import com.example.jobcentrebackend.repository.vacancy.EmploymentHistoryRepository;
import com.example.jobcentrebackend.repository.vacancy.JobRequirementsRepository;
import com.example.jobcentrebackend.repository.employer.EmployerRepository;
import com.example.jobcentrebackend.repository.user.UserRepository;
import com.example.jobcentrebackend.repository.vacancy.JobVacanciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class EmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRequirementsRepository jobRequirementsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UnemployedRepository unemployedRepository;

    @Autowired
    private EmploymentHistoryRepository employmentHistoryRepository;

    @Autowired
    private JobVacanciesRepository jobVacanciesRepository;

    public EmployerDto getEmployerName(String employerName) throws EmployerNotFoundException {
        return EmployerDto.toDto(employerRepository
                .findByEmployerName(employerName)
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found"))
        );
    }

    public EmployerDto getEmployerUsername(String username) throws EmployerNotFoundException, UserNotFoundException {
        return EmployerDto.toDto(employerRepository
                .findByUser(userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException("User not found"))
                )
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found"))
        );
    }

    public List<GetJobVacancyResponse> getJobVacancies(String username) throws UserNotFoundException, EmployerNotFoundException {
        EmployerEntity employerEntity = employerRepository.findByUser(userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found")))
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found"));

        var a = employerEntity.getJobVacancyEntity().stream().toList();
        List<GetJobVacancyResponse> listForReturn = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            GetJobVacancyResponse buff = new GetJobVacancyResponse();

            JobVacanciesDto jobVacanciesDto = JobVacanciesDto.toDto(a.get(i));
            JobRequirementsDto jobRequirementsDto = JobRequirementsDto.toDto(
                    jobRequirementsRepository.getReferenceById(a.get(i).getRequirementsId())
            );

            buff.setJobVacancy(jobVacanciesDto);
            buff.setJobRequirement(jobRequirementsDto);
            listForReturn.add(i, buff);
        }

        return listForReturn;
    }

    public String createEmployer(CreateEmployerRequest request) throws EmployerAlreadyExists, UserNotFoundException, UserHasInappropriateRole {
        if (employerRepository.findByEmployerName(request.getEmployerName()).isPresent()) {
            throw new EmployerAlreadyExists("Employer already exists");
        }

        if(userRepository.findByUsername(request.getUsername()).get().getRole() != Role.EMPLOYED) {
            throw new UserHasInappropriateRole("The user has an inappropriate role");
        }

        employerRepository.save(EmployerEntity
                .builder()
                .employerName(request.getEmployerName())
                .address(request.getAddress())
                .user(userRepository
                        .findByUsername(request.getUsername())
                            .orElseThrow(() -> new UserNotFoundException("User not found"))
                )
                .build()
        );

        return "Employer was successfully created";
    }

    public String acceptUnemployed(long vacancyId, String usernameEmployed, String usernameUnemployed) throws UserNotFoundException, EmployerNotFoundException, UnemployedNotFoundException {
        EmployerEntity employerEntity = employerRepository
                .findByUser(userRepository.findByUsername(usernameEmployed)
                        .orElseThrow(() -> new UserNotFoundException("User not found")))
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found"));

        UnemployedEntity unemployedEntity = unemployedRepository
                .findByUser(userRepository.findByUsername(usernameUnemployed)
                        .orElseThrow(() -> new UserNotFoundException("User not found")))
                .orElseThrow(() -> new UnemployedNotFoundException("Unemployed not found"));

        List<JobVacancyEntity> vacancies = employerEntity.getJobVacancyEntity().stream().toList();
        JobVacancyEntity needVacancy = new JobVacancyEntity();

        for(var vacancy: vacancies) {
            if(vacancy.getId() == vacancyId) {
                needVacancy = vacancy;
                break;
            }
        }

        employmentHistoryRepository.save(EmploymentHistoryEntity
                .builder()
                .jobVacancy(needVacancy)
                .employedEntity(employerEntity)
                .unemployedEntity(unemployedEntity)
                .archived(needVacancy.isArchived())
                .employmentDate(new Date(System.currentTimeMillis()))
                .terminationDate(null)
                .terminationReason(null)
                .build()
        );

        Set<UnemployedEntity> unemployedEntities = needVacancy.getUnemployedEntities();

        for (var unemployed: unemployedEntities) {
            if (unemployed.getId() == UnemployedDto.toDto(unemployedEntity).getId()) {
                unemployedEntities.remove(unemployed);
                break;
            }
        }

        needVacancy.setUnemployedEntities(unemployedEntities);

        jobVacanciesRepository.save(needVacancy);

        return "Unemployed was successfully applied";
    }

    public List<AppliesForVacancies> getAppliesForVacancies(String username) throws UserNotFoundException, EmployerNotFoundException {
        EmployerEntity employerEntity = employerRepository
                .findByUser(userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException("User not found")))
                    .orElseThrow(() -> new EmployerNotFoundException("Employer not found"));

        List<JobVacancyEntity> vacancies = employerEntity.getJobVacancyEntity().stream().toList();
        List<JobVacancyEntity> vacanciesWithApplies = new ArrayList<>();
        List<AppliesForVacancies> appliesForVacancies = new ArrayList<>();

        for (JobVacancyEntity jobVacancyEntity : vacancies) {
            if (!jobVacancyEntity.getUnemployedEntities().stream().toList().isEmpty()) {
                vacanciesWithApplies.add(jobVacancyEntity);
            }
        }

        for (JobVacancyEntity jobVacancyEntity: vacanciesWithApplies) {
            JobVacanciesDto jobVacanciesDto = JobVacanciesDto.toDto(jobVacancyEntity);
            JobRequirementsDto jobRequirementsDto = JobRequirementsDto.toDto(
                    jobRequirementsRepository.getReferenceById(jobVacancyEntity.getRequirementsId())
            );

            List<UnemployedDto> unemployedDto = new ArrayList<>();
            List<UnemployedEntity> unemployedEntities = jobVacancyEntity.getUnemployedEntities().stream().toList();

            for (UnemployedEntity unemployedEntity: unemployedEntities) {
                unemployedDto.add(UnemployedDto.toDto(unemployedEntity));
            }

            AppliesForVacancies buffVacancy = new AppliesForVacancies();
            buffVacancy.setJobRequirementsDto(jobRequirementsDto);
            buffVacancy.setJobVacanciesDto(jobVacanciesDto);
            buffVacancy.setUnemployedDto(unemployedDto);

            appliesForVacancies.add(buffVacancy);
        }

        return appliesForVacancies;
    }
}
