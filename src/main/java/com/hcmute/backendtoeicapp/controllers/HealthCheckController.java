package com.hcmute.backendtoeicapp.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/health-check")
public class HealthCheckController {
    public HealthCheckController() {

    }

    @GetMapping
    public String test() {
        return "OK-ANDROID";
    }
}
