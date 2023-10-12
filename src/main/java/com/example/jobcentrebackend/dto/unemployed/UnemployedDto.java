package com.example.jobcentrebackend.dto.unemployed;

import com.example.jobcentrebackend.dto.ability.AbilityDto;
import com.example.jobcentrebackend.entity.unemployed.UnemployedEntity;
import com.example.jobcentrebackend.enums.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnemployedDto {
    private long id;
    private long passportId;
    private String fullName;
    private int age;
    private EducationLevel educationLevel;
    private String educationalInstitution;
    private String educationDocumentData;
    private String speciality;
    private int workExperience;
    private Date registrationDate;
    private List<AbilityDto> abilities;

    public static UnemployedDto toDto(UnemployedEntity entity) {
        return UnemployedDto
                .builder()
                .id(entity.getId())
                .passportId(entity.getPassportId())
                .fullName(entity.getFullName())
                .age(entity.getAge())
                .educationLevel(entity.getEducationLevel())
                .educationalInstitution(entity.getEducationalInstitution())
                .educationDocumentData(entity.getEducationDocumentData())
                .speciality(entity.getSpecialty())
                .workExperience(entity.getWorkExperience())
                .registrationDate(entity.getRegistrationDate())
                .abilities(entity.getAbility()
                        .stream()
                        .map(AbilityDto::toDto)
                        .toList()
                )
                .build();
    }
}
