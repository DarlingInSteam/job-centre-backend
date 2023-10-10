package com.example.jobcentrebackend.dto.unemployed;

import com.example.jobcentrebackend.entity.unemployed.AbilityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbilityDto {
    private long id;
    private String text;

    public static AbilityDto toDto(AbilityEntity entity) {
        return AbilityDto
                .builder()
                .id(entity.getId())
                .text(entity.getText())
                .build();
    }
}
