package com.tss.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentResponseDto {
    private long departmentId;
    private String departmentName;
    private String location;
}