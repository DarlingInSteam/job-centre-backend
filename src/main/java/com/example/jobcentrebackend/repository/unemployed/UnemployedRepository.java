package com.example.jobcentrebackend.repository.unemployed;

import com.example.jobcentrebackend.entity.unemployed.UnemployedEntity;
import com.example.jobcentrebackend.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnemployedRepository extends JpaRepository<UnemployedEntity, Long> {
    Optional<UnemployedEntity> findByPassportNumber(String number);
    Optional<UnemployedEntity> findByUser(UserEntity user);
    Optional<UnemployedEntity> findById(long id);

}
