package com.example.demo.service;

import com.example.demo.model.ProductCategory;
import com.example.demo.model.ProductDescriptionRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ProductDescriptionService {

    private final ChatClient chatClient;

    public ProductDescriptionService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String generateProductDescription(ProductDescriptionRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (request.getProductName() == null || request.getProductName().isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }

        if (request.getCategory() == null) {
            throw new IllegalArgumentException("Product category is required");
        }

        return chatClient
                .prompt()
                .system(getCategoryPrompt(request.getCategory()))
                .user("Generate a product description for: " + request.getProductName() +
                        ". If the category is MOBILE_PHONE, structure the response as: " +
                        "Product Description, Key Features, and Marketing Tagline in clearly separated sections.")
                .call()
                .content();
    }

    private String getCategoryPrompt(ProductCategory category) {
        switch (category) {
            case ELECTRONICS:
                return "You are an e-commerce electronics expert. Create an attractive product description highlighting features and benefits. " +
                        "Use a polished, customer-focused tone and keep it succinct but descriptive.";
            case CLOTHING:
                return "You are a fashion expert. Create a stylish and appealing product description for clothing. " +
                        "Highlight comfort, design, and everyday value.";
            case BOOKS:
                return "You are a book reviewer. Write an engaging description that encourages customers to read the book. " +
                        "Focus on the value, story, and reading experience.";
            case HOME_APPLIANCES:
                return "You are a home appliance expert. Focus on convenience, usability, and practical benefits. " +
                        "Write a persuasive product description.";
            case MOBILE_PHONE:
                return "You are a mobile technology marketing expert. Create a compelling product description for a mobile phone. " +
                        "Return the response with 3 clearly labeled sections: Product Description, Key Features, and Marketing Tagline.";
            default:
                return "You are a helpful e-commerce assistant.";
        }
    }
}
