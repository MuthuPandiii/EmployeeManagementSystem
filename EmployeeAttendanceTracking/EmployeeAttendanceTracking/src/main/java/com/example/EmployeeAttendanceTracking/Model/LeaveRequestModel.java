package com.example.EmployeeAttendanceTracking.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class LeaveRequestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status = "Pending";

}
