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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(AuthLoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            log.error("Authentication failed for user with email: {}", request.getEmail(), e);
            throw new CustomException("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.error("User with email: {} not found", request.getEmail());
                    return new CustomException("User not found", HttpStatus.NOT_FOUND);
                });

        return authMapper.toResponse(jwtService.generateToken(user), user.getId());
    }

    @Override
    public AuthResponse register(AuthRegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            log.error("Email already in use: {}", request.getEmail());
            throw new CustomException("Email already in use", HttpStatus.CONFLICT);
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userRepository.save(authMapper.toUser(request));
        String token = jwtService.generateToken(user);
        return authMapper.toResponse(token, user.getId());
    }
}
