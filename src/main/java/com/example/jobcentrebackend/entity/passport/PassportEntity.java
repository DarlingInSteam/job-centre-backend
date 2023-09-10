package com.example.jobcentrebackend.entity.passport;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="passports")
public class PassportEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "passport_number")
    private String passportNumber;
    @Column(name = "DateOfBirth")
    private String dateOfBirth;
    @Column(name = "passport_issue_date")
    private String passportIssueDate;
    @Column(name = "passport_issued_by")
    private String passportIssuedBy;
    @Column(name = "address")
    private String address;
    @Column(name = "photo")
    private String photo;
}
