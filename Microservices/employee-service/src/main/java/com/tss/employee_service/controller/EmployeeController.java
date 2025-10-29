package com.tss.employee_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.employee_service.dto.EmpApiResponseDto;
import com.tss.employee_service.dto.EmployeeRequestDto;
import com.tss.employee_service.dto.EmployeeResponseDto;
import com.tss.employee_service.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/empservice/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{emp_id}")
    public ResponseEntity<EmpApiResponseDto> getEmployeeById(@PathVariable("emp_id") long empId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(empId));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto dto) {
        return ResponseEntity.ok(employeeService.saveEmployee(dto));
    }
}