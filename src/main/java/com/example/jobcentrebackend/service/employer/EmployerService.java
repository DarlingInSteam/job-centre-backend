package com.example.jobcentrebackend.service.employer;

import com.example.jobcentrebackend.dto.employer.CreateEmployerRequest;
import com.example.jobcentrebackend.dto.employer.EmployerDto;
import com.example.jobcentrebackend.entity.empoyer.EmployerEntity;
import com.example.jobcentrebackend.exception.employer.EmployerAlreadyExists;
import com.example.jobcentrebackend.exception.employer.EmployerNotFoundException;
import com.example.jobcentrebackend.exception.user.UserNotFoundException;
import com.example.jobcentrebackend.repository.employer.EmployerRepository;
import com.example.jobcentrebackend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private UserRepository userRepository;

    public EmployerDto getEmployerName(String employerName) throws EmployerNotFoundException {
        return EmployerDto.toDto(employerRepository
                .findByEmployerName(employerName)
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found"))
        );
    }

    public EmployerDto getEmployerUserId(long id) throws EmployerNotFoundException, UserNotFoundException {
        return EmployerDto.toDto(employerRepository
                .findByUser(userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User not found"))
                )
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found"))
        );
    }

    public String createEmployer(CreateEmployerRequest request) throws EmployerAlreadyExists, UserNotFoundException {
        if (employerRepository.findByEmployerName(request.getEmployerName()).isPresent()) {
            throw new EmployerAlreadyExists("Employer already exists");
        }

        employerRepository.save(EmployerEntity
                .builder()
                .employerName(request.getEmployerName())
                .address(request.getAddress())
                .user(userRepository.findByPhone(request.getPhone())
                        .orElseThrow(() -> new UserNotFoundException("User not found"))
                )
                .build()
        );

        return "Employer was successfully created";
    }
}
