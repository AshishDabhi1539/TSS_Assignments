package com.tss.department_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.department_service.dto.DepartmentRequestDto;
import com.tss.department_service.dto.DepartmentResponseDto;
import com.tss.department_service.dto.DeptApiResponseDto;
import com.tss.department_service.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/deptservice/departments")
@RefreshScope
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    
    @Value("${app.name}")
    private String name;

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {
    	System.out.println("----------------------------------->"+name);
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{dept_id}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable("dept_id") long deptId) {
        return ResponseEntity.ok(departmentService.getDepartmentById(deptId));
    }

    @PostMapping
    public ResponseEntity<DepartmentResponseDto> createDepartment(@RequestBody DepartmentRequestDto dto) {
        return ResponseEntity.ok(departmentService.saveDepartment(dto));
    }
    
    @GetMapping("/{dept_id}/employees")
    public ResponseEntity<DeptApiResponseDto> getDepartmentWithEmployees(@PathVariable("dept_id") long deptId) {
        return ResponseEntity.ok(departmentService.getDepartmentWithEmployees(deptId));
    }

}
