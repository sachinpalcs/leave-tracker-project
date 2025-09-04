package com.project.leavetracker.Leave.tracker.service;

import com.project.leavetracker.Leave.tracker.dto.request.LeaveRequestDto;
import com.project.leavetracker.Leave.tracker.entity.Employee;
import com.project.leavetracker.Leave.tracker.entity.LeaveRequest;
import com.project.leavetracker.Leave.tracker.enums.LeaveStatus;
import com.project.leavetracker.Leave.tracker.exception.ResourceNotFoundException;
import com.project.leavetracker.Leave.tracker.repository.EmployeeRepository;
import com.project.leavetracker.Leave.tracker.repository.LeaveRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveRequest applyForLeave(Long employeeId, LeaveRequestDto leaveRequestDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployee(employee);
        leaveRequest.setStartDate(leaveRequestDto.getStartDate());
        leaveRequest.setEndDate(leaveRequestDto.getEndDate());
        leaveRequest.setLeaveType(leaveRequestDto.getLeaveType());
        leaveRequest.setReason(leaveRequestDto.getReason());
        leaveRequest.setStatus(LeaveStatus.PENDING);

        return leaveRequestRepository.save(leaveRequest);
    }

    public List<LeaveRequest> getLeaveHistoryForEmployee(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    public List<LeaveRequest> getPendingLeaveRequests() {
        return leaveRequestRepository.findByStatus(LeaveStatus.PENDING);
    }

    public LeaveRequest processLeaveRequest(Long requestId, LeaveStatus status) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found with id: " + requestId));

        leaveRequest.setStatus(status);

        return leaveRequestRepository.save(leaveRequest);
    }
}
