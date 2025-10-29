package com.tss.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponseDto {
    private long employeeId;
    private String name;
    private double salary;
}