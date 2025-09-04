package com.project.leavetracker.Leave.tracker;

import com.project.leavetracker.Leave.tracker.entity.Role;
import com.project.leavetracker.Leave.tracker.enums.RoleName;
import com.project.leavetracker.Leave.tracker.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component

public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if roles already exist to avoid duplicates
        if (roleRepository.findByName(RoleName.ROLE_EMPLOYEE).isEmpty()) {
            Role employeeRole = new Role();
            employeeRole.setName(RoleName.ROLE_EMPLOYEE);
            roleRepository.save(employeeRole);
        }

        if (roleRepository.findByName(RoleName.ROLE_ADMIN).isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName(RoleName.ROLE_ADMIN);
            roleRepository.save(adminRole);
        }
    }
}
