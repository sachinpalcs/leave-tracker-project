package com.project.leavetracker.Leave.tracker.controller;

import com.project.leavetracker.Leave.tracker.dto.request.LeaveActionDto;
import com.project.leavetracker.Leave.tracker.entity.Employee;
import com.project.leavetracker.Leave.tracker.entity.LeaveRequest;
import com.project.leavetracker.Leave.tracker.service.EmployeeService;
import com.project.leavetracker.Leave.tracker.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final LeaveRequestService leaveRequestService;
    private final EmployeeService employeeService;

    @GetMapping("/leaves/all")
    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequests() {
        List<LeaveRequest> allRequests = leaveRequestService.getAllLeaveRequests();
        return ResponseEntity.ok(allRequests);
    }

    @GetMapping("/leaves/pending")
    public ResponseEntity<List<LeaveRequest>> getPendingLeaveRequests() {
        List<LeaveRequest> pendingRequests = leaveRequestService.getPendingLeaveRequests();
        return ResponseEntity.ok(pendingRequests);
    }

    @PostMapping("/leaves/action")
    public ResponseEntity<LeaveRequest> processLeaveRequest(@RequestBody LeaveActionDto leaveActionDto) {
        LeaveRequest updatedRequest = leaveRequestService.processLeaveRequest(leaveActionDto.getRequestId(), leaveActionDto.getStatus());
        return ResponseEntity.ok(updatedRequest);
    }

    @GetMapping("/employees/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
}