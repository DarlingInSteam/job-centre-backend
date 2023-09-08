package com.example.jobcentrebackend.entity.empoyer;

import com.example.jobcentrebackend.entity.JobVacancyEntity;
import com.example.jobcentrebackend.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="employers")
public class EmployerEntity {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "employer_name")
    private String employerName;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "employer")
    private List<JobVacancyEntity> jobVacancies;
}
