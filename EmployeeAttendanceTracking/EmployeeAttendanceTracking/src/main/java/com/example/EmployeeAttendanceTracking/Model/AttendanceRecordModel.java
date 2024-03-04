package com.example.EmployeeAttendanceTracking.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class AttendanceRecordModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    private long employeeId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private double overTime;
}

