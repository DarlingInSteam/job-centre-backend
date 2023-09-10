package com.example.jobcentrebackend.dto.unemployed;

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
public class CreateUnemployedRequest {
    private String fullName;
    private int age;
    private String passportNumber;
    private String passportIssueDate;
    private String dateOfBirth;
    private String passportIssueBy;
    private String address;
    private String photo;
    private EducationLevel educationLevel;
    private String educationalInstitution;
    private String educationDocumentData;
    private String speciality;
    private int workExperience;
    private String username;
}
