package com.example.jobcentrebackend.dto.passport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePassportRequest {
    private String passportNumber;
    private String passportIssueDate;
    private String dateOfBirth;
    private String passportIssueBy;
    private String address;
    private String photo;
}
