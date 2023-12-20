package com.example.jobcentrebackend.service.specialist;

import com.example.jobcentrebackend.dto.specialist.SpecialistDto;
import com.example.jobcentrebackend.entity.specialist.SpecialistEntity;
import com.example.jobcentrebackend.exception.employer.EmployerNotFoundException;
import com.example.jobcentrebackend.exception.specialist.SpecialistNotFoundException;
import com.example.jobcentrebackend.exception.user.UserNotFoundException;
import com.example.jobcentrebackend.repository.specialist.SpecialistRepository;
import com.example.jobcentrebackend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialistService {
    @Autowired
    SpecialistRepository repository;
    @Autowired
    UserRepository userRepository;

    public SpecialistDto getSpecialistByUsername(String username) throws UserNotFoundException, SpecialistNotFoundException {
        return SpecialistDto.toDto(repository
                .findByUser(userRepository.findByUsername(username)
                        .orElseThrow(() -> new UserNotFoundException("User not found"))
                )
                .orElseThrow(() -> new SpecialistNotFoundException("Employer not found"))
        );
    }

    public String createSpecialist(String fullName, String username) throws UserNotFoundException {
        repository.save(SpecialistEntity
                .builder()
                .fullName(fullName)
                .user(userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new UserNotFoundException("User not found"))
                )
                .build()
        );

        return "Specialist was successfully created";
    }
}
