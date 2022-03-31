package com.example.restaurant.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @RequestMapping(path = "/health")
    public String healthCheck(){
        return "Restaurant Service Is UP!";
    }
}
