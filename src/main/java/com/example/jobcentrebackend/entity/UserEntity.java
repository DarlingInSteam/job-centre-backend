package com.example.jobcentrebackend.entity;

import com.example.jobcentrebackend.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue
    private long id;

    @Size(min = 11, message = "{validation.name.size.too_short}")
    @Size(max = 11, message = "{validation.name.size.too_long}")
    private String phone;

    @Size(min = 8, message = "{validation.name.size.too_short}")
    @Size(max = 100, message = "{validation.name.size.too_long}")
    private String password;

    @Enumerated(STRING)
    private Role role;
}
