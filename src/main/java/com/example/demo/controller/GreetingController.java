package com.example.demo.controller;

import com.example.demo.model.ChatRequest;
import com.example.demo.service.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping("/greeting")
    public ResponseEntity<String> generateGreeting(@RequestBody ChatRequest request) {
        String message = greetingService.generateGreeting(request);
        return ResponseEntity.ok(message);
    }
}
