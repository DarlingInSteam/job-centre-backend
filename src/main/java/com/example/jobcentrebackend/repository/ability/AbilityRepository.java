package com.example.jobcentrebackend.repository.ability;

import com.example.jobcentrebackend.entity.ability.AbilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbilityRepository extends JpaRepository<AbilityEntity, Long> {
    Optional<AbilityEntity> findById(long id);
    Optional<AbilityEntity> findByText(String text);

}
