package com.example.EmployeeAttendanceTracking.Repository;

import com.example.EmployeeAttendanceTracking.Model.AttendanceRecordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecordModel, Integer>
{
    @Modifying
    @Transactional
    @Query("UPDATE AttendanceRecordModel a SET a.date = :date, a.startTime = :startTime, a.endTime = :endTime WHERE a.employeeId = :employeeId")
    void updateAttendanceRecord(@Param("employeeId") Long employeeId, @Param("date") LocalDate date, @Param("startTime") LocalTime startTime, @Param("endTime") LocalTime endTime);

    @Modifying
    @Transactional
    @Query("DELETE FROM AttendanceRecordModel a WHERE a.employeeId = :employeeId")
    void deleteAttendanceRecord(@Param("employeeId") Long employeeId);

    @Modifying
    @Transactional
    @Query("Update AttendanceRecordModel a set a.overTime = :overTime where a.recordId = :recordId")
    void updateOverTime(@Param("recordId") Long recordId,@Param("overTime") double overTime);

    List<AttendanceRecordModel> findByEmployeeId(Long employeeId);

    @Query("SELECT e, a FROM EmployeeModel e LEFT JOIN AttendanceRecordModel a ON e.employeeId = a.employeeId WHERE e.departmentId = :departmentId")
    List<Object[]> findEmployeesAndAttendanceRecordsByDepartmentId(@Param("departmentId") Long departmentId);


}
