package com.tss.department_service.service;

import java.util.List;

import com.tss.department_service.dto.DepartmentRequestDto;
import com.tss.department_service.dto.DepartmentResponseDto;

public interface DepartmentService {
    List<DepartmentResponseDto> getAllDepartments();
    DepartmentResponseDto getDepartmentById(long id);
    DepartmentResponseDto saveDepartment(DepartmentRequestDto dto);
}
