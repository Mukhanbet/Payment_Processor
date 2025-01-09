package com.example.Payment_Processor.service.impl;

import com.example.Payment_Processor.config.JwtService;
import com.example.Payment_Processor.exception.CustomException;
import com.example.Payment_Processor.mapper.AuthMapper;
import com.example.Payment_Processor.model.domain.User;
import com.example.Payment_Processor.model.dto.auth.AuthLoginRequest;
import com.example.Payment_Processor.model.dto.auth.AuthRegisterRequest;
import com.example.Payment_Processor.model.dto.auth.AuthResponse;
import com.example.Payment_Processor.repository.UserRepository;
import com.example.Payment_Processor.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(AuthLoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
        return authMapper.toResponse(jwtService.generateToken(user), user.getId());
    }

    @Override
    public AuthResponse register(AuthRegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException("Email already in use", HttpStatus.CONFLICT);
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userRepository.save(authMapper.toUser(request));
        String token = jwtService.generateToken(user);
        return authMapper.toResponse(token, user.getId());
    }
}
