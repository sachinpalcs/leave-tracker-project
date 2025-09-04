package com.project.leavetracker.Leave.tracker.controller;

import com.project.leavetracker.Leave.tracker.dto.request.LoginRequest;
import com.project.leavetracker.Leave.tracker.dto.request.RegisterRequest;
import com.project.leavetracker.Leave.tracker.dto.response.JwtResponse;
import com.project.leavetracker.Leave.tracker.dto.response.MessageResponse;
import com.project.leavetracker.Leave.tracker.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String jwt = authService.login(loginRequest);
        // You can enhance JwtResponse to include roles, email etc.
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
