package com.project.leavetracker.Leave.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEvent {
    private String recipientEmail;
    private String subject;
    private String body;
    private Long leaveRequestId;
}
