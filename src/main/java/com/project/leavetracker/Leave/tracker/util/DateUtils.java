package com.project.leavetracker.Leave.tracker.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    /**
     * Calculates the number of days between two dates, inclusive.
     * For example, from 2024-01-01 to 2024-01-03 is 3 days.
     *
     * @param startDate The start date.
     * @param endDate The end date.
     * @return The total number of days in the period.
     */
    public static long getNumberOfDays(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
            return 0;
        }
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }
}
