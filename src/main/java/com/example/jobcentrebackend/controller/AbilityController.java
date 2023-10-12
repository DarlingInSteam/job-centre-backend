package com.example.jobcentrebackend.controller;

import com.example.jobcentrebackend.service.ability.AbilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ability")
@RequiredArgsConstructor
public class AbilityController {
    @Autowired
    private AbilityService service;

    @GetMapping("/get_all")
    public ResponseEntity getALlAbilities() {
        try {
            return ResponseEntity.ok(service.getAbilities());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestParam String text) {
        try {
            return ResponseEntity.ok(service.createAbility(text));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
