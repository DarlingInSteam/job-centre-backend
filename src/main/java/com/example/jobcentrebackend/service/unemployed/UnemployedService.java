package com.example.jobcentrebackend.service.unemployed;

import com.example.jobcentrebackend.dto.unemployed.CreateUnemployedRequest;
import com.example.jobcentrebackend.dto.unemployed.UnemployedDto;
import com.example.jobcentrebackend.entity.passport.PassportEntity;
import com.example.jobcentrebackend.entity.unemployed.UnemployedEntity;
import com.example.jobcentrebackend.enums.Role;
import com.example.jobcentrebackend.exception.ability.AbilityNotFoundException;
import com.example.jobcentrebackend.exception.unemployed.UnemployedAlreadyExists;
import com.example.jobcentrebackend.exception.unemployed.UnemployedNotFoundException;
import com.example.jobcentrebackend.exception.user.UserHasInappropriateRole;
import com.example.jobcentrebackend.exception.user.UserNotFoundException;
import com.example.jobcentrebackend.repository.passport.PassportRepository;
import com.example.jobcentrebackend.repository.ability.AbilityRepository;
import com.example.jobcentrebackend.repository.unemployed.UnemployedRepository;
import com.example.jobcentrebackend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

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

    @Autowired
    private AbilityRepository abilityRepository;

    public UnemployedDto getUnemployedUsername(String username) throws UserNotFoundException, UnemployedNotFoundException {
        return UnemployedDto.toDto(unemployedRepository
                .findByUser(userRepository.findByUsername(username)
                        .orElseThrow(() -> new UserNotFoundException("User not found"))
                )
                .orElseThrow(() -> new UnemployedNotFoundException("Unemployed not found"))
        );
    }

    public List<UnemployedDto> getAllUnemployed() {
        var unemployedList = unemployedRepository.findAll();
        List<UnemployedDto> unemployedDtoList = new ArrayList<>();

        for (var unemployed: unemployedList) {
            unemployedDtoList.add(UnemployedDto.toDto(unemployed));
        }

        return unemployedDtoList;
    }

    public String createUnemployed(CreateUnemployedRequest request) throws UnemployedAlreadyExists, UserNotFoundException, UserHasInappropriateRole, AbilityNotFoundException {
        List<Long> abilitiesIds = request.getAbilities_ids();

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

        var finalUnemployed = unemployedRepository.save(UnemployedEntity
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
                .ability(new HashSet<>())
                .build()
        );

        if (abilitiesIds != null) {
            addAbility(finalUnemployed, abilitiesIds);
        }

        unemployedRepository.save(finalUnemployed);

        return "Unemployed was successfully created";
    }

    private void addAbility(UnemployedEntity entity, List<Long> abilitiesIds) throws AbilityNotFoundException {
        for (Long id: abilitiesIds) {
            entity.addAbility(abilityRepository
                    .findById(id)
                    .orElseThrow(() -> new AbilityNotFoundException("Ability not found"))
            );
        }
    }

    public String addAboutMe(String aboutMe, Long id) throws UnemployedNotFoundException {
        var entity = unemployedRepository
                .findById(id)
                .orElseThrow(() -> new UnemployedNotFoundException("Unemployed not found"));

        entity.setAboutMe(aboutMe);

        unemployedRepository.save(entity);

        return "About me was successfully added";
    }

    public String addPhoto(String photo, Long id) throws UnemployedNotFoundException {
        var entity = unemployedRepository
                .findById(id)
                .orElseThrow(() -> new UnemployedNotFoundException("Unemployed not found"));

        entity.setUnemployedPhoto(photo);

        unemployedRepository.save(entity);

        return "Photo was successfully added";
    }
}
