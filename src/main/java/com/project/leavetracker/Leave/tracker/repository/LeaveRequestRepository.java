package com.project.leavetracker.Leave.tracker.repository;

import com.project.leavetracker.Leave.tracker.entity.LeaveRequest;
import com.project.leavetracker.Leave.tracker.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(Long employeeId);
    List<LeaveRequest> findByStatus(LeaveStatus status);
}