package com.tss.employee_service.service;

import java.util.List;

import com.tss.employee_service.dto.EmpApiResponseDto;
import com.tss.employee_service.dto.EmployeeRequestDto;
import com.tss.employee_service.dto.EmployeeResponseDto;

public interface EmployeeService {
    List<EmployeeResponseDto> getAllEmployees();
    EmpApiResponseDto getEmployeeById(long id);
    EmployeeResponseDto saveEmployee(EmployeeRequestDto dto);
    List<EmployeeResponseDto> getEmployeesByDepartmentId(long deptId);
    EmpApiResponseDto updateEmployeeDepartment(long empId, long deptId);
}
	