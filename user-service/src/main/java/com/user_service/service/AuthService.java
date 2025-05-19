package com.user_service.service;

import com.user_service.DTO.LoginRequest;
import com.user_service.Security.JwtTokenUtil;
import com.user_service.model.User;
import com.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "User registered successfully";
    }

    public Map<String, String> login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        try {
            String token = jwtTokenUtil.generateToken(
                    new org.springframework.security.core.userdetails.User(
                            user.getUsername(), user.getPassword(),
                            List.of(new SimpleGrantedAuthority(user.getRole()))
                    )
            );
            return Map.of("token", token);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT token: " + e.getMessage(), e);
        }
    }
}

