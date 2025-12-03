package com.project.leavetracker.Leave.tracker.service;

import com.project.leavetracker.Leave.tracker.dto.NotificationEvent;
import com.project.leavetracker.Leave.tracker.dto.request.LeaveRequestDto;
import com.project.leavetracker.Leave.tracker.entity.Employee;
import com.project.leavetracker.Leave.tracker.entity.LeaveRequest;
import com.project.leavetracker.Leave.tracker.enums.LeaveStatus;
import com.project.leavetracker.Leave.tracker.exception.ResourceNotFoundException;
import com.project.leavetracker.Leave.tracker.kafka.NotificationProducer;
import com.project.leavetracker.Leave.tracker.repository.EmployeeRepository;
import com.project.leavetracker.Leave.tracker.repository.LeaveRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final NotificationProducer notificationProducer;

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

        LeaveRequest savedRequest = leaveRequestRepository.save(leaveRequest);
        NotificationEvent event = new NotificationEvent(
                "admin@company.com", // In a real app, this would be the manager's email
                "New Leave Request Applied",
                "Employee " + employee.getUsername() + " has applied for " + leaveRequestDto.getLeaveType(),
                savedRequest.getId()
        );
        notificationProducer.sendNotification(event);
        return savedRequest;
//        return leaveRequestRepository.save(leaveRequest);
    }

    public LeaveRequest processLeaveRequest(Long requestId, LeaveStatus status) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found with id: " + requestId));

        leaveRequest.setStatus(status);
        LeaveRequest updatedRequest = leaveRequestRepository.save(leaveRequest);

        NotificationEvent event = new NotificationEvent(
                leaveRequest.getEmployee().getEmail(),
                "Leave Request " + status,
                "Your leave request has been " + status,
                updatedRequest.getId()
        );

        notificationProducer.sendNotification(event);

        return updatedRequest;
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

//    public LeaveRequest processLeaveRequest(Long requestId, LeaveStatus status) {
//        LeaveRequest leaveRequest = leaveRequestRepository.findById(requestId)
//                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found with id: " + requestId));
//
//        leaveRequest.setStatus(status);
//
//        return leaveRequestRepository.save(leaveRequest);
//    }
}
