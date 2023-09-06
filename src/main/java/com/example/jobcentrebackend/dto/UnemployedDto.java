package com.example.jobcentrebackend.dto;

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
    private int passportNumber;
    private Date passportIssueDate;
    private String passportIssueBy;
    private String address;
    private String phone;
    private String photo;
    private EducationLevel educationLevel;
    private String educationalInstitution;
    private String educationDocumentData;
    private String speciality;
    private int workExperience;
    private Date registrationDate;
}
