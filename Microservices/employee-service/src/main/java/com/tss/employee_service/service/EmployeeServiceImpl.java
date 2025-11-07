package com.tss.employee_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.tss.employee_service.dto.DepartmentResponseDto;
import com.tss.employee_service.dto.EmpApiResponseDto;
import com.tss.employee_service.dto.EmployeeRequestDto;
import com.tss.employee_service.dto.EmployeeResponseDto;
import com.tss.employee_service.entity.Employee;
import com.tss.employee_service.exception.ApiException;
import com.tss.employee_service.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;
	//private final RestTemplate restTemplate;
	private final DepartmentApiClient deptApiClient;

	private final WebClient webClient;
	
	@Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(emp -> modelMapper.map(emp, EmployeeResponseDto.class))
                .collect(Collectors.toList());
    }

	@Override
	public EmpApiResponseDto getEmployeeById(long id) {
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ApiException("Employee not found with ID: " + id));

//		ResponseEntity<DepartmentResponseDto> departmentResponse = restTemplate.getForEntity("http://localhost:8081/deptservice/departments/" + emp.getDeptId(), DepartmentResponseDto.class);

//		DepartmentResponseDto departmentResponse = webClient
//	                .get()
//	                .uri("http://localhost:8081/deptservice/departments/" + emp.getDeptId())
//	                .retrieve()
//	                .bodyToMono(DepartmentResponseDto.class)
//	                .block(); 
		
		DepartmentResponseDto departmentResponse = deptApiClient.getDepartmentById(emp.getDeptId());
		 
		EmpApiResponseDto response = new EmpApiResponseDto();
        response.setEmployee(modelMapper.map(emp, EmployeeResponseDto.class));
        response.setDepartment(departmentResponse);
        return response;
	}

	@Override
	public EmployeeResponseDto saveEmployee(EmployeeRequestDto dto) {
	    DepartmentResponseDto departmentResponse;
	    try {
	        departmentResponse = webClient
	                .get()
	                .uri("http://localhost:8081/deptservice/departments/" + dto.getDeptId())
	                .retrieve()
	                .bodyToMono(DepartmentResponseDto.class)
	                .block();

	        if (departmentResponse == null || departmentResponse.getDepartmentId() == 0) {
	            throw new ApiException("Department not found with ID: " + dto.getDeptId());
	        }
	    } catch (Exception ex) {
	        throw new ApiException("Department not found with ID: " + dto.getDeptId());
	    }

	    Employee emp = modelMapper.map(dto, Employee.class);
	    Employee saved = employeeRepository.save(emp);

	    return modelMapper.map(saved, EmployeeResponseDto.class);
	}
	
	@Override
	public List<EmployeeResponseDto> getEmployeesByDepartmentId(long deptId) {
	    return employeeRepository.findByDeptId(deptId)
	            .stream()
	            .map(emp -> modelMapper.map(emp, EmployeeResponseDto.class))
	            .collect(Collectors.toList());
	}

	@Override
	public EmpApiResponseDto updateEmployeeDepartment(long empId, long deptId) {
	    // Step 1: Validate employee
	    Employee emp = employeeRepository.findById(empId)
	            .orElseThrow(() -> new ApiException("Employee not found with ID: " + empId));

	    // Step 2: Validate department via WebClient
	    DepartmentResponseDto departmentResponse;
	    try {
	        departmentResponse = webClient
	                .get()
	                .uri("http://localhost:8081/deptservice/departments/" + deptId)
	                .retrieve()
	                .bodyToMono(DepartmentResponseDto.class)
	                .block();

	        if (departmentResponse == null || departmentResponse.getDepartmentId() == 0) {
	            throw new ApiException("Department not found with ID: " + deptId);
	        }
	    } catch (Exception ex) {
	        throw new ApiException("Department not found with ID: " + deptId);
	    }

	    // Step 3: Update employee department
	    emp.setDeptId(deptId);
	    Employee updated = employeeRepository.save(emp);

	    // Step 4: Prepare combined response (Employee + Department)
	    EmpApiResponseDto response = new EmpApiResponseDto();
	    response.setMessage("Employee department updated successfully");
	    response.setEmployee(modelMapper.map(updated, EmployeeResponseDto.class));
	    response.setDepartment(departmentResponse);

	    return response;
	}

}