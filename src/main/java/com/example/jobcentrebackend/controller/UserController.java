package com.example.jobcentrebackend.controller;

import com.example.jobcentrebackend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity getUserByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.getUserUsername(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user_employed")
    public ResponseEntity getUserEmployedByUsername(@RequestParam String username) {
        try {
            return ResponseEntity.ok(userService.getUserEmployer(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user_unemployed")
    public ResponseEntity getUserUnemployedByUsername(@RequestParam String username) {
        try {
            return ResponseEntity.ok(userService.getUserUnemployed(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
