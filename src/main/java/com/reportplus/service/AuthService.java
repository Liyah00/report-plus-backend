package com.reportplus.service;

import com.reportplus.dto.LoginRequest;
import com.reportplus.dto.LoginResponse;
import com.reportplus.model.User;
import com.reportplus.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmailAndPassword(
                        request.getEmail(),
                        request.getPassword()
                )
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
            throw new RuntimeException("Account is not active");
        }

        LoginResponse response = new LoginResponse();

        response.setUserId(user.getUserId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setStatus(user.getStatus());

        if (user.getOrganization() != null) {
            response.setOrganizationId(
                    user.getOrganization().getOrganizationId()
            );
        }

        return response;
    }
}