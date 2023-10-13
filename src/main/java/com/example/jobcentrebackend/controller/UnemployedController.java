package com.example.jobcentrebackend.controller;

import com.example.jobcentrebackend.dto.unemployed.CreateUnemployedRequest;
import com.example.jobcentrebackend.exception.user.UserNotFoundException;
import com.example.jobcentrebackend.service.unemployed.UnemployedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unemployed")
public class UnemployedController {
    @Autowired
    private UnemployedService service;

    @PostMapping("/create")
    public ResponseEntity createUnemployed(@RequestBody CreateUnemployedRequest request) {
        try {
            return ResponseEntity.ok(service.createUnemployed(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add_about_me")
    public ResponseEntity addAboutMe(@RequestParam String aboutMe, @RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.addAboutMe(aboutMe, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add_photo")
    public ResponseEntity addPhoto(@RequestParam String photo, @RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.addPhoto(photo, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get_unemployed")
    public ResponseEntity getUnemployedByUsername(@RequestParam String username) {
        try {
            return ResponseEntity.ok(service.getUnemployedUsername(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get_all_unemployed")
    public ResponseEntity getALlUnemployed() {
        try {
            return ResponseEntity.ok(service.getAllUnemployed());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
