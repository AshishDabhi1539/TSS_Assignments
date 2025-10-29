package com.tss.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EmpApiResponseDto {

	private EmployeeResponseDto employee;
	private DepartmentResponseDto department;
}
