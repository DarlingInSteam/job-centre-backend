package com.example.jobcentrebackend.controller;

import com.example.jobcentrebackend.dto.employer.CreateEmployerRequest;
import com.example.jobcentrebackend.service.employer.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employer")
@RequiredArgsConstructor
public class EmployerController {
    @Autowired
    private EmployerService service;

    @PostMapping("/create")
    public ResponseEntity createEmployer(@RequestBody CreateEmployerRequest request) {
        try {
            return ResponseEntity.ok(service.createEmployer(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get_employer")
    public ResponseEntity getEmployerByUsername(@RequestParam String username) {
        try {
            return ResponseEntity.ok(service.getEmployerUsername(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get_vacancies")
    public ResponseEntity getEmployerVacancies(@RequestParam String username) {
        try {
            return ResponseEntity.ok(service.getJobVacancies(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get_applies_vacancies")
    public ResponseEntity getAppliesVacancies(@RequestParam String username) {
        try {
            return ResponseEntity.ok(service.getAppliesForVacancies(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
