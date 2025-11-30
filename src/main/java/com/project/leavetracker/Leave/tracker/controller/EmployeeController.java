package com.project.leavetracker.Leave.tracker.controller;

import com.project.leavetracker.Leave.tracker.dto.response.EmployeeResponse;
import com.project.leavetracker.Leave.tracker.entity.Employee;
import com.project.leavetracker.Leave.tracker.exception.ResourceNotFoundException;
import com.project.leavetracker.Leave.tracker.repository.EmployeeRepository;
import com.project.leavetracker.Leave.tracker.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/me")
    public ResponseEntity<EmployeeResponse> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Employee employee = employeeRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        EmployeeResponse employeeResponse = new EmployeeResponse(
                employee.getId(),
                employee.getUsername(),
                employee.getEmail(),
                employee.getRoles().stream().map(role -> role.getName().toString()).collect(Collectors.toSet())
        );
        return ResponseEntity.ok(employeeResponse);
    }
}