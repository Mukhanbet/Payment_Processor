package com.example.Payment_Processor.mapper.impl;

import com.example.Payment_Processor.mapper.AuthMapper;
import com.example.Payment_Processor.model.domain.User;
import com.example.Payment_Processor.model.dto.auth.AuthRegisterRequest;
import com.example.Payment_Processor.model.dto.auth.AuthResponse;
import com.example.Payment_Processor.model.enums.Role;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthMapperImpl implements AuthMapper {
    @Override
    public User toUser(AuthRegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);
        return user;
    }

    @Override
    public AuthResponse toResponse(String token, Long id) {
        AuthResponse response = new AuthResponse();
        response.setId(id);
        response.setToken(token);
        return response;
    }
}
