package com.example.jobcentrebackend.entity.specialist;

import com.example.jobcentrebackend.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="specialist")
public class SpecialistEntity {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "full_name")
    private String fullName;
}
