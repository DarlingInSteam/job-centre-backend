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

    @GetMapping("/{job_title}")
    public ResponseEntity getJobVacancy(@PathVariable String job_title) {
        try {
            return ResponseEntity.ok(service.getJobVacancy(job_title));
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
}
