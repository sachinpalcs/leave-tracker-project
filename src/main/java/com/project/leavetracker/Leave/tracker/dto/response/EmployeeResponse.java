package com.project.leavetracker.Leave.tracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String username;
    private String email;
    private Set<String> roles;
}
