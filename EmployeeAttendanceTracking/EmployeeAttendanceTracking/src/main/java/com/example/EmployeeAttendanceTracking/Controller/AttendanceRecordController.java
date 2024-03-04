package com.example.EmployeeAttendanceTracking.Controller;

import com.example.EmployeeAttendanceTracking.Model.AttendanceRecordModel;
import com.example.EmployeeAttendanceTracking.Model.EmployeeModel;
import com.example.EmployeeAttendanceTracking.Service.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/AttendanceRecord")
public class AttendanceRecordController
{
    @Autowired
    AttendanceRecordService attendanceRecordService;

    @PostMapping("/addAttendanceRecord")
    public AttendanceRecordModel addAttendanceRecord(@RequestBody AttendanceRecordModel attendanceRecordModel) {
        return attendanceRecordService.createAttendanceRecord(attendanceRecordModel);
    }

    @PostMapping("/addAllAttendanceRecord")
    public List<AttendanceRecordModel> addAllAttendanceRecord(@RequestBody List<AttendanceRecordModel> attendanceRecordModel) {
        return attendanceRecordService.createAllAttendanceRecord(attendanceRecordModel);
    }

    @GetMapping("/getAllAttendanceRecords")
    public List<AttendanceRecordModel> getAllAttendanceRecords() {
        return attendanceRecordService.readAllAttendanceRecords();
    }

    @GetMapping("/getAttendanceRecordById/{employeeId}")
    public AttendanceRecordModel getAttendanceRecordById(@PathVariable Long employeeId) {
        return attendanceRecordService.readAttendanceRecordById(employeeId);
    }

    @PutMapping("/updateAttendanceRecord/{employeeId}")
    public String updateAttendanceRecord(@PathVariable Long employeeId, @RequestBody AttendanceRecordModel attendanceRecordModel) {
        return attendanceRecordService.updateAttendanceRecord(employeeId, attendanceRecordModel.getDate(), attendanceRecordModel.getStartTime(), attendanceRecordModel.getEndTime());
    }

    @DeleteMapping("/deleteAttendanceRecord/{employeeId}")
    public String deleteAttendanceRecord(@PathVariable Long employeeId) {
        return attendanceRecordService.deleteAttendanceRecord(employeeId);
    }

    @GetMapping("/generateEmployeeAttendanceReport/{employeeId}")
    public List<Map<String, Object>> generate(@PathVariable Long employeeId) {
        List<AttendanceRecordService.AttendanceSummary> attendanceSummaries = attendanceRecordService.calculateWorkingHoursPerDay(employeeId);

        List<Map<String, Object>> response = new ArrayList<>();
        for (AttendanceRecordService.AttendanceSummary summary : attendanceSummaries) {
            Map<String, Object> summaryMap = new HashMap<>();
            summaryMap.put("employeeId", summary.getEmployeeId());
            summaryMap.put("date", summary.getDate());
            summaryMap.put("workingHours", summary.getFormattedWorkingHours());

            response.add(summaryMap);
        }

        return response;
    }


    @GetMapping("/generateDepartmentAttendanceReport/{departmentId}")
    public List<Map<String, Object>> generateDepartmentAttendanceReport(@PathVariable Long departmentId) {
        List<AttendanceRecordService.AttendanceSummary> attendanceSummaries = attendanceRecordService.calculateWorkingHoursPerDayForDepartment(departmentId);

        List<Map<String, Object>> response = new ArrayList<>();
        for (AttendanceRecordService.AttendanceSummary summary : attendanceSummaries) {
            Map<String, Object> summaryMap = new HashMap<>();
            summaryMap.put("employeeId", summary.getEmployeeId());
            summaryMap.put("date", summary.getDate());
            summaryMap.put("workingHours", summary.getFormattedWorkingHours());

            response.add(summaryMap);
        }

        return response;
    }

    @PutMapping("/calculateOverTime")
    public String calculateOverTime(){
        return attendanceRecordService.calculateOverTime();
    }

}

