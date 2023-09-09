package com.example.jobcentrebackend.entity.employer;

import com.example.jobcentrebackend.entity.vacancy.JobVacancyEntity;
import com.example.jobcentrebackend.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
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

    @OneToMany
    @JoinTable(
            name = "employer_job_vacancies",
            joinColumns = @JoinColumn(name = "employer_id"),
            inverseJoinColumns = @JoinColumn(name = "vacancy_id")
    )
    private Set<JobVacancyEntity> jobVacancyEntity;
}
