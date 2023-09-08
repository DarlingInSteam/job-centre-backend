package com.example.jobcentrebackend.dto.unemployed;

import com.example.jobcentrebackend.entity.unemployed.UnemployedEntity;
import com.example.jobcentrebackend.enums.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnemployedDto {
    private long id;
    private String fullName;
    private int age;
    private String passportNumber;
    private Date passportIssueDate;
    private String passportIssueBy;
    private String address;
    private String photo;
    private EducationLevel educationLevel;
    private String educationalInstitution;
    private String educationDocumentData;
    private String speciality;
    private int workExperience;
    private Date registrationDate;

    public static UnemployedDto toDto(UnemployedEntity entity) {
        return UnemployedDto
                .builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .age(entity.getAge())
                .passportNumber(entity.getPassportNumber())
                .passportIssueDate(entity.getPassportIssueDate())
                .passportIssueBy(entity.getPassportIssuedBy())
                .address(entity.getAddress())
                .photo(entity.getPhoto())
                .educationLevel(entity.getEducationLevel())
                .educationalInstitution(entity.getEducationalInstitution())
                .educationDocumentData(entity.getEducationDocumentData())
                .speciality(entity.getSpecialty())
                .workExperience(entity.getWorkExperience())
                .registrationDate(entity.getRegistrationDate())
                .build();
    }
}
