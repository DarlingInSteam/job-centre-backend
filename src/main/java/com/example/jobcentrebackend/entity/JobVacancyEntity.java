package com.example.jobcentrebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private EmployerEntity employer;

    @Column(name = "salary")
    private int salary;

    @Column(name = "archived")
    private boolean archived;
}
