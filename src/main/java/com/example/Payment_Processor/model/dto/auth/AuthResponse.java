package com.example.Payment_Processor.model.dto.auth;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthResponse {
    private Long id;
    private String token;
}
