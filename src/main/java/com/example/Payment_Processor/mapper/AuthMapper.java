package com.example.Payment_Processor.mapper;

import com.example.Payment_Processor.model.domain.User;
import com.example.Payment_Processor.model.dto.auth.AuthRegisterRequest;
import com.example.Payment_Processor.model.dto.auth.AuthResponse;


public interface AuthMapper {
    User toUser(AuthRegisterRequest request);
    AuthResponse toResponse(String token, Long id);
}
