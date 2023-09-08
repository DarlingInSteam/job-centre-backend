package com.example.jobcentrebackend.repository.user;

import com.example.jobcentrebackend.entity.user.UnemployedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnemployedRepository extends JpaRepository<UnemployedEntity, Long> {
    Optional<UnemployedEntity> findByPassportNumber(String number);
}
