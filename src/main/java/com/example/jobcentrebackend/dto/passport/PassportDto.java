package com.example.jobcentrebackend.dto.passport;

import com.example.jobcentrebackend.dto.unemployed.UnemployedDto;
import com.example.jobcentrebackend.entity.passport.PassportEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassportDto {
    private String passportNumber;
    private String passportIssueDate;
    private String dateOfBirth;
    private String passportIssueBy;
    private String address;
    private String photo;

    public static PassportDto toDto(PassportEntity entity) {
        return PassportDto
                .builder()
                .address(entity.getAddress())
                .dateOfBirth(entity.getDateOfBirth())
                .passportIssueBy(entity.getPassportIssuedBy())
                .passportNumber(entity.getPassportNumber())
                .passportIssueDate(entity.getPassportIssueDate())
                .photo(entity.getPhoto())
                .build();
    }
}
