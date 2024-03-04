package com.example.EmployeeAttendanceTracking.Controller;

import com.example.EmployeeAttendanceTracking.Model.EmployeeModel;
import com.example.EmployeeAttendanceTracking.Model.LeaveRequestModel;
import com.example.EmployeeAttendanceTracking.Service.EmployeeService;
import com.example.EmployeeAttendanceTracking.Service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/LeaveRequest")
public class LeaveRequestController
{
    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping("/addLeaveRequest")
    public LeaveRequestModel addLeaveRequest(@RequestBody LeaveRequestModel leaveRequestModel) {
        return leaveRequestService.createLeaveRequest(leaveRequestModel);
    }

    @PostMapping("/addAllLeaveRequest")
    public List<LeaveRequestModel> addAllLeaveRequest(@RequestBody List<LeaveRequestModel> leaveRequestModels) {
        return leaveRequestService.createAllLeaveRequest(leaveRequestModels);
    }

    @GetMapping("/getAllLeaveRequests")
    public List<LeaveRequestModel> getAllLeaveRequests() {
        return leaveRequestService.readAllLeaveRequests();
    }

    @GetMapping("/getLeaveRequestById/{employeeId}")
    public LeaveRequestModel getLeaveRequestById(@PathVariable Long employeeId) {
        return leaveRequestService.readLeaveRequestById(employeeId);
    }

    @PutMapping("/updateLeaveRequest/{employeeId}")
    public String updateLeaveRequest(@PathVariable Long employeeId, @RequestBody LeaveRequestModel leaveRequestModel) {
        return leaveRequestService.updateLeaveRequest(employeeId, leaveRequestModel.getStartDate(), leaveRequestModel.getEndDate());
    }

    @DeleteMapping("/deleteLeaveRequest/{employeeId}")
    public String deleteLeaveRequest(@PathVariable Long employeeId) {
        return leaveRequestService.deleteLeaveRequest(employeeId);
    }

    @PutMapping("/approveLeaveRequest/{employeeId}")
    public String approveLeaveRequest(@PathVariable Long employeeId){
        return leaveRequestService.approveLeaveRequest(employeeId);
    }

    @PutMapping("/declineLeaveRequest/{employeeId}")
    public String declineLeaveRequest(@PathVariable Long employeeId){
        return leaveRequestService.declineLeaveRequest(employeeId);
    }
}

