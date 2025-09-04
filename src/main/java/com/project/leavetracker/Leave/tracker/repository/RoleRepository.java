package com.project.leavetracker.Leave.tracker.repository;

import com.project.leavetracker.Leave.tracker.entity.Role;
import com.project.leavetracker.Leave.tracker.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName name);
}
