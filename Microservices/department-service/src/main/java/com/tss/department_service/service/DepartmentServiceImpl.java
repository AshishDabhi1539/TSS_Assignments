package com.tss.department_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tss.department_service.dto.DepartmentRequestDto;
import com.tss.department_service.dto.DepartmentResponseDto;
import com.tss.department_service.entity.Department;
import com.tss.department_service.exception.ApiException;
import com.tss.department_service.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(dept -> new DepartmentResponseDto(dept.getDepartmentId(), dept.getDepartmentName(), dept.getLocation()))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponseDto getDepartmentById(long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ApiException("Department not found with ID: " + id));
        return new DepartmentResponseDto(dept.getDepartmentId(), dept.getDepartmentName(), dept.getLocation());
    }

    @Override
    public DepartmentResponseDto saveDepartment(DepartmentRequestDto dto) {
        Department dept = new Department();
        dept.setDepartmentName(dto.getDepartmentName());
        dept.setLocation(dto.getLocation());
        Department saved = departmentRepository.save(dept);
        return new DepartmentResponseDto(saved.getDepartmentId(), saved.getDepartmentName(), saved.getLocation());
    }
}
