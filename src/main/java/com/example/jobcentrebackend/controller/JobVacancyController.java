package com.example.jobcentrebackend.controller;

import com.example.jobcentrebackend.dto.vacancy.CreateJobVacancyRequest;
import com.example.jobcentrebackend.service.vacancy.JobVacanciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job_vacancy")
@RequiredArgsConstructor
public class JobVacancyController {
    @Autowired
    private JobVacanciesService service;

    @PostMapping("/create")
    public ResponseEntity createJobVacancy(@RequestBody CreateJobVacancyRequest request) {
        try {
            return ResponseEntity.ok(service.createVacancy(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{job_id}")
    public ResponseEntity getJobVacancy(@PathVariable Long job_id) {
        try {
            return ResponseEntity.ok(service.getJobVacancy(job_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get_all_vacancy")
    public ResponseEntity getAllVacancy() {
        try {
            return ResponseEntity.ok(service.getAllVacancy());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/apply_vacancy")
    public ResponseEntity applyVacancyUnemployed(@RequestParam long id, @RequestParam String username) {
        try {
            return ResponseEntity.ok(service.ApplyVacancyUnemployed(id, username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/archived_vacancy")
    public ResponseEntity archivedVacancy(@RequestParam long id) {
        try {
            return ResponseEntity.ok(service.archivedVacancy(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update_vacancy")
    public ResponseEntity updateVacancy(@RequestParam CreateJobVacancyRequest request, @RequestParam long id) {
        try {
            return ResponseEntity.ok(service.updateVacancy(request, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
