package com.example.jobcentrebackend.entity.vacancy;

import com.example.jobcentrebackend.entity.employer.EmployerEntity;
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

    @Column(name = "salary")
    private int salary;

    @Column(name = "archived")
    private boolean archived;

    @Column(name = "requirements_id")
    private long requirementsId;

    @ManyToOne
    private EmployerEntity employerEntity;
}
