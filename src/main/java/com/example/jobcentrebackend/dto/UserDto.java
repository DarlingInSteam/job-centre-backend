package com.example.jobcentrebackend.dto;

import com.example.jobcentrebackend.entity.UserEntity;
import com.example.jobcentrebackend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String phone;
    private Role role;

    public static UserDto toDto(UserEntity entity) {
        return UserDto
                .builder()
                .id(entity.getId())
                .phone(entity.getPhone())
                .role(entity.getRole())
                .build();
    }
}