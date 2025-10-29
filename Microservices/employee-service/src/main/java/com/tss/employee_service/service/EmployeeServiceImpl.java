package com.tss.employee_service.service;

import java.util.List;
import java.util.stream.Collectors;

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
	
	//private final RestTemplate restTemplate;

	private final WebClient webClient;
	
	@Override
	public List<EmployeeResponseDto> getAllEmployees() {
		return employeeRepository.findAll().stream()
				.map(emp -> new EmployeeResponseDto(emp.getEmployeeId(), emp.getName(), emp.getSalary()))
				.collect(Collectors.toList());
	}

	@Override
	public EmpApiResponseDto getEmployeeById(long id) {
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ApiException("Employee not found with ID: " + id));

		//ResponseEntity<DepartmentResponseDto> departmentResponse = restTemplate.getForEntity("http://localhost:8081/deptservice/departments/" + emp.getDeptId(), DepartmentResponseDto.class);

		DepartmentResponseDto departmentWebClient = webClient
	                .get()
	                .uri("http://localhost:8081/deptservice/departments/" + emp.getDeptId())
	                .retrieve()
	                .bodyToMono(DepartmentResponseDto.class)
	                .block(); 
		 
		EmpApiResponseDto response = new EmpApiResponseDto();

		response.setEmployee(mapToResponseDto(emp));
		response.setDepartment(departmentWebClient);
		return response;
	}

	private EmployeeResponseDto mapToResponseDto(Employee employee) {
		return new EmployeeResponseDto(employee.getEmployeeId(), employee.getName(), employee.getSalary());
	}

	@Override
	public EmployeeResponseDto saveEmployee(EmployeeRequestDto dto) {
		Employee emp = new Employee();
		emp.setName(dto.getName());
		emp.setSalary(dto.getSalary());
		Employee saved = employeeRepository.save(emp);
		return new EmployeeResponseDto(saved.getEmployeeId(), saved.getName(), saved.getSalary());
	}

}