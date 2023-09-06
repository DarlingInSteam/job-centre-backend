package com.example.jobcentrebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employer {
    private long id;
    private String employerName;
    private String address;
    private String phone;
}
