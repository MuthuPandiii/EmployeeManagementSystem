package com.example.EmployeeAttendanceTracking.Repository;

import com.example.EmployeeAttendanceTracking.Model.LeaveRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequestModel, Long>
{
    @Modifying
    @Transactional
    @Query("UPDATE LeaveRequestModel l SET l.startDate = :startDate, l.endDate = :endDate, l.status = :status WHERE l.employeeId = :employeeId")
    void updateLeaveRequest(@Param("employeeId") Long employeeId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("status") String status);

    @Modifying
    @Transactional
    @Query("DELETE FROM LeaveRequestModel l WHERE l.employeeId = :employeeId")
    void deleteLeaveRequest(@Param("employeeId") Long employeeId);
}
