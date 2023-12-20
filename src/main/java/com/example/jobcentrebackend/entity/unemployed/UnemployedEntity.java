package com.example.jobcentrebackend.entity.unemployed;

import com.example.jobcentrebackend.entity.ability.AbilityEntity;
import com.example.jobcentrebackend.entity.user.UserEntity;
import com.example.jobcentrebackend.entity.vacancy.JobVacancyEntity;
import com.example.jobcentrebackend.enums.EducationLevel;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="unemployed")
public class UnemployedEntity {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "passport_id")
    private long passportId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "age")
    private int age;

    @Column(name = "education_level")
    @Enumerated(STRING)
    private EducationLevel educationLevel;

    @Column(name = "educational_institution")
    private String educationalInstitution;

    @Column(name = "education_document_data")
    private String educationDocumentData;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "work_experience")
    private int workExperience;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "unemployed_photo")
    private String unemployedPhoto;

    @ManyToMany
    private Set<JobVacancyEntity> jobVacancyEntities;

    @ManyToMany
    @JoinTable(
            name = "invite_vacancies",
            joinColumns = @JoinColumn(name = "unemployed_id"),
            inverseJoinColumns = @JoinColumn(name = "vacancy_id")
    )
    private Set<JobVacancyEntity> entities;

    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
            name = "unemployed_ability",
            joinColumns = @JoinColumn(name = "unemployed_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    private Set<AbilityEntity> ability;

    public UnemployedEntity addAbility(AbilityEntity entity) {
        ability.add(entity);
        entity.getUnemployed().add(this);
        return this;
    }
}
