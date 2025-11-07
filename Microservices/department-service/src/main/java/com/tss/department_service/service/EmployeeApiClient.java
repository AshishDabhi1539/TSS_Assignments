package com.tss.department_service.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tss.department_service.dto.EmployeeResponseDto;

@FeignClient(name = "employee-service")
public interface EmployeeApiClient {

	@GetMapping("/empservice/employees/department/{deptId}")
	public List<EmployeeResponseDto> getEmployeesByDepartment(@PathVariable Long deptId);
	
	

}
