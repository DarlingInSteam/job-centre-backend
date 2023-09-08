package com.example.jobcentrebackend.service.unemployed;

import com.example.jobcentrebackend.dto.unemployed.CreateUnemployedRequest;
import com.example.jobcentrebackend.dto.unemployed.UnemployedDto;
import com.example.jobcentrebackend.entity.unemployed.UnemployedEntity;
import com.example.jobcentrebackend.exception.unemployed.UnemployedAlreadyExists;
import com.example.jobcentrebackend.exception.unemployed.UnemployedNotFoundException;
import com.example.jobcentrebackend.exception.user.UserNotFoundException;
import com.example.jobcentrebackend.repository.unemployed.UnemployedRepository;
import com.example.jobcentrebackend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UnemployedService {
    @Autowired
    private UnemployedRepository unemployedRepository;

    @Autowired
    private UserRepository userRepository;

    public UnemployedDto getUnemployedUserId(long id) throws UserNotFoundException, UnemployedNotFoundException {
        return UnemployedDto.toDto(unemployedRepository
                .findByUser(userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User not found"))
                )
                .orElseThrow(() -> new UnemployedNotFoundException("Unemployed noy found"))
        );
    }

    public String createUnemployed(CreateUnemployedRequest request) throws UnemployedAlreadyExists, UserNotFoundException {
        if(unemployedRepository.findByPassportNumber(request.getPassportNumber()).isPresent()) {
            throw new UnemployedAlreadyExists("Unemployed already exists");
        }

        unemployedRepository.save(UnemployedEntity
                .builder()
                .age(request.getAge())
                .fullName(request.getFullName())
                .photo(request.getPhoto())
                .educationLevel(request.getEducationLevel())
                .educationalInstitution(request.getEducationalInstitution())
                .passportNumber(request.getPassportNumber())
                .address(request.getAddress())
                .educationDocumentData(request.getEducationDocumentData())
                .passportIssueDate(request.getPassportIssueDate())
                .passportIssuedBy(request.getPassportIssueBy())
                .specialty(request.getSpeciality())
                .workExperience(request.getWorkExperience())
                .registrationDate(new Date(System.currentTimeMillis()))
                .user(userRepository.findByUsername(request.getUsername())
                        .orElseThrow(() -> new UserNotFoundException("User not found"))
                )
                .build()
        );

        return "Unemployed was successfully created";
    }
}
