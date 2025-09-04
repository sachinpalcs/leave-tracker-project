package com.project.leavetracker.Leave.tracker.dto.response;

import com.project.leavetracker.Leave.tracker.enums.LeaveStatus;
import com.project.leavetracker.Leave.tracker.enums.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class LeaveRequestResponse {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveType leaveType;
    private LeaveStatus status;
    private LocalDate requestDate;
    private EmployeeResponse employee; // Nested DTO
}
