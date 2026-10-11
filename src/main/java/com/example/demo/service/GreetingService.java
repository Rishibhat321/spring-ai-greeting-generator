package com.example.demo.service;

import com.example.demo.model.ChatRequest;
import com.example.demo.model.RoleType;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private final ChatClient chatClient;

    public GreetingService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String generateGreeting(ChatRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (request.getRole() == null) {
            throw new IllegalArgumentException("Role is required");
        }

        return chatClient
                .prompt()
                .system(getRolePrompt(request.getRole()))
                .user("Generate a greeting for the person named: " + request.getName())
                .call()
                .content();
    }

    private String getRolePrompt(RoleType role) {
        switch (role) {
            case STUDENT:
                return "You are a supportive mentor. Write a short, warm greeting for a student. " +
                        "Keep the tone motivating and friendly. " +
                        "Do not include extra explanations or metadata. Return only the greeting text.";
            case TEACHER:
                return "You are a respectful assistant. Write a warm greeting for a teacher. " +
                        "Highlight appreciation, guidance, and student growth. " +
                        "Return only the greeting text.";
            case DEVELOPER:
                return "You are a friendly tech mentor. Write a greeting for a developer. " +
                        "Keep it energetic and motivational, focused on coding and building software. " +
                        "Return only the greeting text.";
            case MANAGER:
                return "You are a leadership coach. Write a concise and motivating greeting for a manager. " +
                        "Focus on confidence, leadership, and team success. " +
                        "Return only the greeting text.";
            default:
                return "You are a helpful assistant. Write a friendly greeting with a positive tone.";
        }
    }
}
