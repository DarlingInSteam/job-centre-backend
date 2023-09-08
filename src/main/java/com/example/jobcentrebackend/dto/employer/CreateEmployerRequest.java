package com.example.jobcentrebackend.dto.employer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployerRequest {
    private String employerName;
    private String address;
    private String phone;
}
