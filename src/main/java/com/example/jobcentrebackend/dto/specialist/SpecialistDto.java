package com.example.jobcentrebackend.dto.specialist;

import com.example.jobcentrebackend.entity.specialist.SpecialistEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistDto {
    private long id;
    private String fullName;

    public static SpecialistDto toDto(SpecialistEntity entity) {
        return SpecialistDto.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .build();
    }
}
