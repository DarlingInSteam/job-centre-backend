package com.example.jobcentrebackend.dto.auth;

import com.example.jobcentrebackend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String phone;
    private String password;
    private Role role;
}
