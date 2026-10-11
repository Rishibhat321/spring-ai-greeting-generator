package com.example.demo.controller;

import com.example.demo.model.ProductDescriptionRequest;
import com.example.demo.service.ProductDescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductDescriptionController {

    private final ProductDescriptionService productDescriptionService;

    public ProductDescriptionController(ProductDescriptionService productDescriptionService) {
        this.productDescriptionService = productDescriptionService;
    }

    @PostMapping("/product")
    public ResponseEntity<String> generateProductDescription(@RequestBody ProductDescriptionRequest request) {
        String description = productDescriptionService.generateProductDescription(request);
        return ResponseEntity.ok(description);
    }
}
