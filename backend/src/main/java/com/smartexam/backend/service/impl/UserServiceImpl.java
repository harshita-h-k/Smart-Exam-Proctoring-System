package com.smartexam.backend.service.impl;

// DTO Imports
import com.smartexam.backend.dto.LoginRequest;
import com.smartexam.backend.dto.LoginResponse;
import com.smartexam.backend.dto.RegisterRequest;
import com.smartexam.backend.dto.UserDto;

// Entity Importartexam.backend
import com.smartexam.backend.entity.User;

// Repository Import
import com.smartexam.backend.repository.UserRepository;

// Security Import
import com.smartexam.backend.security.JwtService;

// Service Import
import com.smartexam.backend.service.UserService;

// Third-party Imports
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public UserDto register(RegisterRequest registerRequest) {

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());

        // Encrypt password
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        user.setRole(registerRequest.getRole());
        user.setEnabled(true);

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        LoginResponse response = new LoginResponse();
        response.setToken(jwtService.generateToken(user.getUsername()));
        response.setUsername(user.getUsername());
        response.setRole(user.getRole().name());
        response.setMessage("Login Successful");

        return response;
    }

    @Override
    public UserDto getCurrentUser(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void changePassword(String username, String newPassword) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    }
}