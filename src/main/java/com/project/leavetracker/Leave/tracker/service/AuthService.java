package com.project.leavetracker.Leave.tracker.service;

import com.project.leavetracker.Leave.tracker.dto.request.LoginRequest;
import com.project.leavetracker.Leave.tracker.dto.request.RegisterRequest;
import com.project.leavetracker.Leave.tracker.entity.Employee;
import com.project.leavetracker.Leave.tracker.entity.Role;
import com.project.leavetracker.Leave.tracker.enums.RoleName;
import com.project.leavetracker.Leave.tracker.exception.ResourceNotFoundException;
import com.project.leavetracker.Leave.tracker.repository.EmployeeRepository;
import com.project.leavetracker.Leave.tracker.repository.RoleRepository;
import com.project.leavetracker.Leave.tracker.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken(authentication);
    }

    public void register(RegisterRequest registerRequest) {
        if (employeeRepository.existsByUsername(registerRequest.getUsername())) {
            throw new IllegalArgumentException("Username is already taken!");
        }

        if (employeeRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email Address already in use!");
        }

        Employee employee = new Employee(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword())
        );

        Role userRole = roleRepository.findByName(RoleName.ROLE_EMPLOYEE)
                .orElseThrow(() -> new ResourceNotFoundException("User Role not set."));

        employee.setRoles(Collections.singleton(userRole));

        employeeRepository.save(employee);
    }
}

