package com.example.jobcentrebackend.service.ability;

import com.example.jobcentrebackend.dto.ability.AbilityDto;
import com.example.jobcentrebackend.entity.ability.AbilityEntity;
import com.example.jobcentrebackend.exception.ability.AbilityAlreadyExists;
import com.example.jobcentrebackend.repository.ability.AbilityRepository;
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
