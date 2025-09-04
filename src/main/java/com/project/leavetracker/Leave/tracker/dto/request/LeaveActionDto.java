package com.project.leavetracker.Leave.tracker.dto.request;

import com.project.leavetracker.Leave.tracker.enums.LeaveStatus;
import lombok.Data;

@Data
public class LeaveActionDto {
    private Long requestId;
    private LeaveStatus status;
}
