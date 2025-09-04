package com.project.leavetracker.Leave.tracker.dto.request;

import com.project.leavetracker.Leave.tracker.enums.LeaveType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveType leaveType;
    private String reason;
}