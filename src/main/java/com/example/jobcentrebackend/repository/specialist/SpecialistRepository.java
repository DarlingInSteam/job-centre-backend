package com.example.jobcentrebackend.repository.specialist;

import com.example.jobcentrebackend.entity.specialist.SpecialistEntity;
import com.example.jobcentrebackend.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialistRepository extends JpaRepository<SpecialistEntity, Long> {
    Optional<SpecialistEntity> findById(long id);
    Optional<SpecialistEntity> findByUser(UserEntity user);
}
