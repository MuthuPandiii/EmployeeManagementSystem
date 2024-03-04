package com.example.EmployeeAttendanceTracking.Repository;

import com.example.EmployeeAttendanceTracking.Model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long>
{
    @Query("SELECT e FROM EmployeeModel e LEFT JOIN AttendanceRecordModel a ON e.employeeId = a.employeeId WHERE e.departmentId = :departmentId")
    List<EmployeeModel> findByDepartmentId(@Param("departmentId") Long departmentId);

    @Modifying
    @Transactional
    @Query("UPDATE EmployeeModel e SET e.name = :name, e.department = :department, e.position = :position, e.contact = :contact WHERE e.employeeId = :employeeId")
    void updateEmployee(@Param("employeeId") Long employeeId, @Param("name") String name, @Param("department") String department, @Param("position") String position, @Param("contact") String contact);

    @Modifying
    @Transactional
    @Query("DELETE FROM EmployeeModel e WHERE e.employeeId = :employeeId")
    void deleteEmployee(@Param("employeeId") Long employeeId);


}

