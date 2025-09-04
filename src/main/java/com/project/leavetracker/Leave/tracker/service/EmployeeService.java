package com.project.leavetracker.Leave.tracker.service;

import com.project.leavetracker.Leave.tracker.entity.Employee;
import com.project.leavetracker.Leave.tracker.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();

    }
}