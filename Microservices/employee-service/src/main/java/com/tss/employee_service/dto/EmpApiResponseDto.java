package com.tss.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpApiResponseDto {
    private String message;
    private EmployeeResponseDto employee;
    private DepartmentResponseDto department;
}
