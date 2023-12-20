package com.example.jobcentrebackend.controller;

import com.example.jobcentrebackend.dto.employer.CreateEmployerRequest;
import com.example.jobcentrebackend.service.employer.EmployerService;
import com.example.jobcentrebackend.service.specialist.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/specialist")
@RequiredArgsConstructor
public class SpecialistController {
    @Autowired
    private SpecialistService service;
    @PostMapping("/create")
    public ResponseEntity createSpecialist(@RequestParam String fullName, @RequestParam String username ) {
        try {
            return ResponseEntity.ok(service.createSpecialist(fullName, username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get_specialist")
    public ResponseEntity getSpecialistByUsername(@RequestParam String username ) {
        try {
            return ResponseEntity.ok(service.getSpecialistByUsername(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
