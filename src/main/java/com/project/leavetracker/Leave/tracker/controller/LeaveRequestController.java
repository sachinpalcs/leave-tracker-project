package com.project.leavetracker.Leave.tracker.controller;

import com.project.leavetracker.Leave.tracker.dto.request.LeaveRequestDto;
import com.project.leavetracker.Leave.tracker.entity.LeaveRequest;
import com.project.leavetracker.Leave.tracker.security.UserPrincipal;
import com.project.leavetracker.Leave.tracker.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @PostMapping("/apply")
    public ResponseEntity<LeaveRequest> applyForLeave(@AuthenticationPrincipal UserPrincipal currentUser, @RequestBody LeaveRequestDto leaveRequestDto) {
        LeaveRequest newLeaveRequest = leaveRequestService.applyForLeave(currentUser.getId(), leaveRequestDto);
        return ResponseEntity.ok(newLeaveRequest);
    }

    @GetMapping("/history")
    public ResponseEntity<List<LeaveRequest>> getLeaveHistory(@AuthenticationPrincipal UserPrincipal currentUser) {
        List<LeaveRequest> leaveHistory = leaveRequestService.getLeaveHistoryForEmployee(currentUser.getId());
        return ResponseEntity.ok(leaveHistory);
    }
}