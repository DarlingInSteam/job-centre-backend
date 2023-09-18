package com.example.jobcentrebackend.service.unemployed;

import com.example.jobcentrebackend.dto.unemployed.CreateUnemployedRequest;
import com.example.jobcentrebackend.dto.unemployed.UnemployedDto;
import com.example.jobcentrebackend.entity.passport.PassportEntity;
import com.example.jobcentrebackend.entity.unemployed.UnemployedEntity;
import com.example.jobcentrebackend.enums.Role;
import com.example.jobcentrebackend.exception.unemployed.UnemployedAlreadyExists;
import com.example.jobcentrebackend.exception.unemployed.UnemployedNotFoundException;
import com.example.jobcentrebackend.exception.user.UserHasInappropriateRole;
import com.example.jobcentrebackend.exception.user.UserNotFoundException;
import com.example.jobcentrebackend.repository.passport.PassportRepository;
import com.example.jobcentrebackend.repository.unemployed.UnemployedRepository;
import com.example.jobcentrebackend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UnemployedService {
    @Autowired
    private UnemployedRepository unemployedRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PassportRepository passportRepository;

    public UnemployedDto getUnemployedUsername(String username) throws UserNotFoundException, UnemployedNotFoundException {
        return UnemployedDto.toDto(unemployedRepository
                .findByUser(userRepository.findByUsername(username)
                        .orElseThrow(() -> new UserNotFoundException("User not found"))
                )
                .orElseThrow(() -> new UnemployedNotFoundException("Unemployed noy found"))
        );
    }

    public String createUnemployed(CreateUnemployedRequest request) throws UnemployedAlreadyExists, UserNotFoundException, UserHasInappropriateRole {
        if(unemployedRepository.findByUser(userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"))).isPresent()) {
            throw new UnemployedAlreadyExists("Unemployed already exists");
        }

        if(userRepository.findByUsername(request.getUsername()).get().getRole() != Role.UNEMPLOYED) {
            throw new UserHasInappropriateRole("The user has an inappropriate role");
        }

        var a = passportRepository.save(PassportEntity
                .builder()
                .dateOfBirth(request.getDateOfBirth())
                .passportNumber(passwordEncoder.encode(request.getPassportNumber()))
                .passportIssueDate(request.getPassportIssueDate())
                .passportIssuedBy(request.getPassportIssueBy())
                .photo(request.getPhoto())
                .address(request.getAddress())
                .build()
        );

        var passportId = a.getId();

        unemployedRepository.save(UnemployedEntity
                .builder()
                .age(request.getAge())
                .passportId(passportId)
                .fullName(request.getFullName())
                .educationLevel(request.getEducationLevel())
                .educationalInstitution(request.getEducationalInstitution())
                .educationDocumentData(request.getEducationDocumentData())
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
