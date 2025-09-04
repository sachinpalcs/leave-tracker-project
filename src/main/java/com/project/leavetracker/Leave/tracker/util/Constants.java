package com.project.leavetracker.Leave.tracker.util;

public final class Constants {

    // Role Constants
    public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    // Exception Messages
    public static final String USER_NOT_FOUND_MSG = "User not found with username: ";
    public static final String ROLE_NOT_FOUND_MSG = "Error: Role is not found.";
    public static final String EMPLOYEE_NOT_FOUND_MSG = "Employee not found with id: ";

    // Private constructor to prevent instantiation
    private Constants() {
        throw new IllegalStateException("Utility class");
    }
}
