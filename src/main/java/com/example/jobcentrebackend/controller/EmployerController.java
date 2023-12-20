package com.example.jobcentrebackend.controller;

import com.example.jobcentrebackend.dto.employer.AcceptUnemployedRequest;
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

    @PostMapping("/add_photo")
    public ResponseEntity addPhoto(@RequestParam String photo, @RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.addCompanyPhoto(photo, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add_about_company")
    public ResponseEntity addAboutCompany(@RequestParam String aboutCompany, @RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.addAboutCompany(aboutCompany, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add_new_name")
    public ResponseEntity addNewName(@RequestParam String newName, @RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.addCompanyName(newName, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add_new_address")
    public ResponseEntity addNewAddress(@RequestParam String newAddress, @RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.addCompanyAddress(newAddress, id));
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

    @PostMapping("/accept_unemployed")
    public ResponseEntity acceptUnemployed(@RequestBody AcceptUnemployedRequest request) {
        try {
            return ResponseEntity.ok(service.acceptUnemployed(request.getVacancyId(), request.getUsernameEmployed(), request.getUsernameUnemployed()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reject_unemployed")
    public ResponseEntity rejectUnemployed(@RequestBody AcceptUnemployedRequest request) {
        try {
            return ResponseEntity.ok(service.rejectUnemployed(request.getVacancyId(), request.getUsernameEmployed(), request.getUsernameUnemployed()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
