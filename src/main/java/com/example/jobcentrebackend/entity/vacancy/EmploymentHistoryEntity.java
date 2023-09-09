package com.example.jobcentrebackend.entity.vacancy;

import com.example.jobcentrebackend.entity.employer.EmployerEntity;
import com.example.jobcentrebackend.entity.unemployed.UnemployedEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="employment_history")
public class EmploymentHistoryEntity {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "unemployed_id")
    private UnemployedEntity unemployedEntity;

    @OneToOne
    @JoinColumn(name = "job_vacancy_id")
    private JobVacancyEntity jobVacancie;

    @OneToOne
    @JoinColumn(name = "employer_id")
    private EmployerEntity employedEntity;

    @Column(name = "employment_date")
    private Date employmentDate;

    @Column(name = "termination_date")
    private Date terminationDate;

    @Column(name = "termination_reason")
    private String terminationReason;

    @Column(name = "archived")
    private boolean archived;
}
