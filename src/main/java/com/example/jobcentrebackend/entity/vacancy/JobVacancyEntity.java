package com.example.jobcentrebackend.entity.vacancy;

import com.example.jobcentrebackend.entity.employer.EmployerEntity;
import com.example.jobcentrebackend.entity.unemployed.UnemployedEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="job_vacancies")
public class JobVacancyEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "job_type")
    private String jobType;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "salary")
    private int salary;

    @Column(name = "archived")
    private boolean archived;

    @Column(name = "requirements_id")
    private long requirementsId;

    @ManyToOne
    private EmployerEntity employerEntity;

    @ManyToMany
    private Set<EmployerEntity> employerEntityInvite;

    @ManyToMany
    @JoinTable(
            name = "vacancies_apply_unemployed",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "unemployed_id")
    )
    private Set<UnemployedEntity>  unemployedEntities;
}