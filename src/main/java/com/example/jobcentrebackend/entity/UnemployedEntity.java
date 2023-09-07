package com.example.jobcentrebackend.entity;

import com.example.jobcentrebackend.enums.EducationLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="unemployed")
public class UnemployedEntity {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "age")
    private int age;
    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "passport_issue_date")
    private Date passportIssueDate;
    @Column(name = "passport_issued_by")
    private String passportIssuedBy;
    @Column(name = "address")
    private String address;
    @Column(name = "photo")
    private String photo;
    @Column(name = "education_level")
    private EducationLevel educationLevel;
    @Column(name = "educational_institution")
    private String educationalInstitution;
    @Column(name = "education_document_data")
    private String educationDocumentData;
    @Column(name = "specialty")
    private String specialty;
    @Column(name = "work_experience")
    private int workExperience;
    @Column(name = "registration_date")
    private Date registrationDate;
}
