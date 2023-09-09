package com.example.jobcentrebackend.service.employer;

import com.example.jobcentrebackend.dto.employer.CreateEmployerRequest;
import com.example.jobcentrebackend.dto.employer.EmployerDto;
import com.example.jobcentrebackend.dto.vacancy.GetJobVacancyResponse;
import com.example.jobcentrebackend.dto.vacancy.JobRequirementsDto;
import com.example.jobcentrebackend.dto.vacancy.JobVacanciesDto;
import com.example.jobcentrebackend.entity.employer.EmployerEntity;
import com.example.jobcentrebackend.enums.Role;
import com.example.jobcentrebackend.exception.employer.EmployerAlreadyExists;
import com.example.jobcentrebackend.exception.employer.EmployerNotFoundException;
import com.example.jobcentrebackend.exception.user.UserHasInappropriateRole;
import com.example.jobcentrebackend.exception.user.UserNotFoundException;
import com.example.jobcentrebackend.repository.vacancy.JobRequirementsRepository;
import com.example.jobcentrebackend.repository.employer.EmployerRepository;
import com.example.jobcentrebackend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRequirementsRepository jobRequirementsRepository;

    @Autowired
    private UserRepository userRepository;

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
                .user(userRepository.findByUsername(request.getUsername())
                        .orElseThrow(() -> new UserNotFoundException("User not found"))
                )
                .build()
        );

        return "Employer was successfully created";
    }
}
