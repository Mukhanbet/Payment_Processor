package com.example.Payment_Processor.service;

import com.example.Payment_Processor.model.dto.auth.AuthLoginRequest;
import com.example.Payment_Processor.model.dto.auth.AuthRegisterRequest;
import com.example.Payment_Processor.model.dto.auth.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthLoginRequest request);
    AuthResponse register(AuthRegisterRequest request);
}
