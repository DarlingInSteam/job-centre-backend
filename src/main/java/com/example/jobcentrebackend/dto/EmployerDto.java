package com.example.jobcentrebackend.dto;

import com.example.jobcentrebackend.entity.EmployerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployerDto {
    private long id;
    private String employerName;
    private String address;

    public static EmployerDto toDto(EmployerEntity entity) {
        return EmployerDto
                .builder()
                .id(entity.getId())
                .employerName(entity.getEmployerName())
                .address(entity.getAddress())
                .build();
    }
}
