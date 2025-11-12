package com.tss.department_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;

import com.tss.department_service.dto.DepartmentRequestDto;
import com.tss.department_service.dto.DepartmentResponseDto;
import com.tss.department_service.dto.DeptApiResponseDto;
import com.tss.department_service.dto.EmployeeResponseDto;
import com.tss.department_service.entity.Department;
import com.tss.department_service.exception.ApiException;
import com.tss.department_service.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    //private final WebClient webClient;
    private final EmployeeApiClient empClient;

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(dept -> modelMapper.map(dept, DepartmentResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponseDto getDepartmentById(long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ApiException("Department not found with ID: " + id));
        return modelMapper.map(dept, DepartmentResponseDto.class);
    }

    @Override
    public DepartmentResponseDto saveDepartment(DepartmentRequestDto dto) {
        Department dept = modelMapper.map(dto, Department.class);
        Department saved = departmentRepository.save(dept);
        return modelMapper.map(saved, DepartmentResponseDto.class);
    }

    @Override
    public DeptApiResponseDto getDepartmentWithEmployees(long deptId) {
        Department dept = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ApiException("Department not found with ID: " + deptId));

        DepartmentResponseDto departmentDto = modelMapper.map(dept, DepartmentResponseDto.class);

//        List<EmployeeResponseDto> employees = webClient.get()
//                .uri("http://localhost:8080/empservice/employees/department/" + deptId)
//                .retrieve()
//                .bodyToFlux(EmployeeResponseDto.class)
//                .collectList()
//                .block();
        List<EmployeeResponseDto> employees = empClient.getEmployeesByDepartment(deptId);


        DeptApiResponseDto response = new DeptApiResponseDto();
        response.setDepartment(departmentDto);
        response.setEmployees(employees);
        return response;
    }

}
