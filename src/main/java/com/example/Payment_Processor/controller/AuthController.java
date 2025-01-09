package com.example.Payment_Processor.controller;

import com.example.Payment_Processor.model.dto.auth.AuthLoginRequest;
import com.example.Payment_Processor.model.dto.auth.AuthRegisterRequest;
import com.example.Payment_Processor.model.dto.auth.AuthResponse;
import com.example.Payment_Processor.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthLoginRequest request) {
       return authService.login(request);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRegisterRequest request) {
        return authService.register(request);
    }
}
