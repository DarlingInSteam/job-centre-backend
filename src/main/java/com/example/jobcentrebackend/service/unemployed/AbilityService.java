package com.example.jobcentrebackend.service.unemployed;

import com.example.jobcentrebackend.dto.unemployed.AbilityDto;
import com.example.jobcentrebackend.entity.unemployed.AbilityEntity;
import com.example.jobcentrebackend.exception.unemployed.AbilityAlreadyExists;
import com.example.jobcentrebackend.repository.unemployed.AbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbilityService {
    @Autowired
    private AbilityRepository repository;

    public List<AbilityDto> getAbilities() {
        return repository.findAll()
                .stream()
                .map(AbilityDto::toDto)
                .toList();
    }

    public String createAbility(String text) throws AbilityAlreadyExists {
        if (repository.findByText(text).isPresent())
            throw new AbilityAlreadyExists("Ability already exists");

        repository.save(AbilityEntity
                .builder()
                .text(text)
                .build()
        );

        return "Ability was successfully created";
    }
}
