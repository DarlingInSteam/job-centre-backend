package com.example.jobcentrebackend.repository.passport;

import com.example.jobcentrebackend.entity.passport.PassportEntity;
import com.example.jobcentrebackend.entity.unemployed.UnemployedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassportRepository extends JpaRepository<PassportEntity, Long> {
    Optional<PassportEntity> findByPassportNumber(String number);
    Optional<PassportEntity> findById(long id);

}
