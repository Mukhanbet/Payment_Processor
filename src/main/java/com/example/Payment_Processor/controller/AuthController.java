package com.example.Payment_Processor.controller;

import com.example.Payment_Processor.model.dto.auth.AuthLoginRequest;
import com.example.Payment_Processor.model.dto.auth.AuthRegisterRequest;
import com.example.Payment_Processor.model.dto.auth.AuthResponse;
import com.example.Payment_Processor.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_XML_VALUE)
    public AuthResponse login(@Valid @RequestBody AuthLoginRequest request) {
       return authService.login(request);
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_XML_VALUE)
    public AuthResponse register(@Valid @RequestBody AuthRegisterRequest request) {
        return authService.register(request);
    }
}
