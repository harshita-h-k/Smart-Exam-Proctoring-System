package com.smartexam.backend.service;

import com.smartexam.backend.dto.LoginRequest;
import com.smartexam.backend.dto.LoginResponse;
import com.smartexam.backend.dto.RegisterRequest;
import com.smartexam.backend.dto.UserDto;

public interface UserService {

    // Register a new user
    UserDto register(RegisterRequest registerRequest);

    // Login user
    LoginResponse login(LoginRequest loginRequest);

    // Get current logged-in user
    UserDto getCurrentUser(String username);

    // Change password
    void changePassword(String username, String newPassword);
}