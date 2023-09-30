package com.example.jobcentrebackend.controller;

import com.example.jobcentrebackend.dto.passport.CreatePassportRequest;
import com.example.jobcentrebackend.service.passport.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passport")
@RequiredArgsConstructor
public class PassportController {
    @Autowired
    private PassportService service;

    @GetMapping("/{passport_id}")
    public ResponseEntity getPassportById(@PathVariable long passport_id) {
        try {
            return ResponseEntity.ok(service.getPassportById(passport_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/update_passport")
    public ResponseEntity updatePassport(@RequestBody CreatePassportRequest request, @RequestParam long id) {
        try {
            return ResponseEntity.ok(service.updatePassport(request, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/remove_passport")
    public ResponseEntity removePassport(@RequestParam long id) {
        try {
            return ResponseEntity.ok(service.removePassport(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
