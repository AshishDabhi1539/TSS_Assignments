package com.tss.department_service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptApiResponseDto {
    private DepartmentResponseDto department;
    private List<EmployeeResponseDto> employees;
}