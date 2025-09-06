package com.tss.hibernateDemo.service;

import java.util.List;

import com.tss.hibernateDemo.dto.EmployeeRequest;
import com.tss.hibernateDemo.dto.EmployeeResponse;

public interface EmployeeService {
	
	EmployeeResponse create(EmployeeRequest request);
    EmployeeResponse getById(int id);
    List<EmployeeResponse> getAll();
    EmployeeResponse update(int id, EmployeeRequest request);
    void delete(int id);

}
