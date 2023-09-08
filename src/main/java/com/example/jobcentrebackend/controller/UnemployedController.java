package com.example.jobcentrebackend.controller;

import com.example.jobcentrebackend.service.unemployed.UnemployedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unemployed")
public class UnemployedController {
    @Autowired
    private UnemployedService service;


}
