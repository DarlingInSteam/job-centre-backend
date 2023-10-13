package com.example.jobcentrebackend.entity.employer;

import com.example.jobcentrebackend.entity.vacancy.JobVacancyEntity;
import com.example.jobcentrebackend.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Random;
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

    @Column(name = "company_photo")
    private String companyPhoto;

    @Column(name = "about_company")
    private String aboutCompany;

    @OneToMany
    @JoinTable(
            name = "employer_job_vacancies",
            joinColumns = @JoinColumn(name = "employer_id"),
            inverseJoinColumns = @JoinColumn(name = "vacancy_id")
    )
    private Set<JobVacancyEntity> jobVacancyEntity;
}
