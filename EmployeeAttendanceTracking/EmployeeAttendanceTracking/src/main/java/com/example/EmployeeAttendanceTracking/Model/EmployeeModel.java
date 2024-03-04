package com.example.EmployeeAttendanceTracking.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class EmployeeModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private Long departmentId;
    private String name;
    private String department;
    private String position;
    private String contact;
    private double basePay;
    private double hikeRate;
}
