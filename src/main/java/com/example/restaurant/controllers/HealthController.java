package com.example.restaurant.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping(path = "/health")
    public String healthCheck(){
        return "Restaurant Service Is UP!";
    }

}
