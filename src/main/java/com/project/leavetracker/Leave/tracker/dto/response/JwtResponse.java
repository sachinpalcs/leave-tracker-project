package com.project.leavetracker.Leave.tracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;
    private final String tokenType = "Bearer";
}