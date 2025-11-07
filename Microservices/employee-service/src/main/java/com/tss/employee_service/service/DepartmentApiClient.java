package com.tss.employee_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tss.employee_service.dto.DepartmentResponseDto;

@FeignClient(name = "department-service")
public interface DepartmentApiClient {

	@GetMapping("/deptservice/departments/{dept_id}")
    public DepartmentResponseDto getDepartmentById(@PathVariable long dept_id);
}
	