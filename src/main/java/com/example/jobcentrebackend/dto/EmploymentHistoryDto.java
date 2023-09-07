package com.example.jobcentrebackend.dto;

import com.example.jobcentrebackend.entity.EmploymentHistoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentHistoryDto {
    private long id;
    private Date employmentDate;
    private Date terminationDate;
    private String terminationReason;
    private boolean archived;

    public static EmploymentHistoryDto toDto(EmploymentHistoryEntity entity) {
        return EmploymentHistoryDto
                .builder()
                .id(entity.getId())
                .employmentDate(entity.getEmploymentDate())
                .terminationDate(entity.getTerminationDate())
                .terminationReason(entity.getTerminationReason())
                .archived(entity.isArchived())
                .build();
    }
}
