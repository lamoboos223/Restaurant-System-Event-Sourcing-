package com.example.restaurant.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    
    @RequestMapping(path = "/health1")
    public String healthCheck(){
        return "Restaurant Service Is UP!";
    }


    @RequestMapping(path = "/health")
    public String checkEndpoint(){
        return "it's working !";
    }
}
