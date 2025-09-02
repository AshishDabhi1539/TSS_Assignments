package com.tss.hibernateDemo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tss.hibernateDemo.dto.EmployeeRequest;
import com.tss.hibernateDemo.dto.EmployeeResponse;
import com.tss.hibernateDemo.dto.SalaryAccountResponse;
import com.tss.hibernateDemo.entity.Employee;
import com.tss.hibernateDemo.entity.SalaryAccount;
import com.tss.hibernateDemo.exception.ResourceNotFoundException;
import com.tss.hibernateDemo.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        Employee entity = new Employee();
        entity.setName(request.getName());
        entity.setSalary(request.getSalary());

        SalaryAccount account = new SalaryAccount();
        account.setAccountNumber(request.getSalaryAccount().getAccountNumber());
        account.setBankName(request.getSalaryAccount().getBankName());
        account.setBranch(request.getSalaryAccount().getBranch());
        account.setIfscCode(request.getSalaryAccount().getIfscCode());

        entity.setSalaryAccount(account);

        Employee saved = employeeRepository.save(entity);
        return toResponse(saved);
    }

    @Override
    public EmployeeResponse getById(int id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee " + id + " not found"));
        return toResponse(emp);
    }

    @Override
    public List<EmployeeResponse> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> responses = new ArrayList<>();

        for (Employee e : employees) {
            responses.add(toResponse(e));
        }
        return responses;
    }

    @Override
    public EmployeeResponse update(int id, EmployeeRequest request) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee " + id + " not found"));

        existing.setName(request.getName());
        existing.setSalary(request.getSalary());

        SalaryAccount acc = existing.getSalaryAccount();
        if (acc == null) {
            acc = new SalaryAccount();
            existing.setSalaryAccount(acc);
        }
        acc.setAccountNumber(request.getSalaryAccount().getAccountNumber());
        acc.setBankName(request.getSalaryAccount().getBankName());
        acc.setBranch(request.getSalaryAccount().getBranch());
        acc.setIfscCode(request.getSalaryAccount().getIfscCode());

        Employee saved = employeeRepository.save(existing);
        return toResponse(saved);
    }

    @Override
    public void delete(int id) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee " + id + " not found"));
        employeeRepository.delete(existing);
    }

    private EmployeeResponse toResponse(Employee e) {
        EmployeeResponse res = mapper.map(e, EmployeeResponse.class);

        if (e.getSalaryAccount() != null) {
            SalaryAccountResponse ar = mapper.map(e.getSalaryAccount(), SalaryAccountResponse.class);
            res.setSalaryAccount(ar);
        }
        return res;
    }
	
}
