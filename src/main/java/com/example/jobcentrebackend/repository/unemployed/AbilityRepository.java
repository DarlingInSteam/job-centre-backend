package com.example.jobcentrebackend.repository.unemployed;

import com.example.jobcentrebackend.entity.unemployed.AbilityEntity;
import com.example.jobcentrebackend.entity.unemployed.UnemployedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbilityRepository extends JpaRepository<AbilityEntity, Long> {
    Optional<AbilityEntity> findById(long id);
    Optional<AbilityEntity> findByText(String text);

}
