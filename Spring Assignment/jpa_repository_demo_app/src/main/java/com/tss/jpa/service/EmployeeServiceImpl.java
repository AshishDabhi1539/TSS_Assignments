package com.tss.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.jpa.entity.Employee;
import com.tss.jpa.repository.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;
    
    @Override
    public List<Employee> readAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findEmployeeById(int id) {
        return employeeRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findEmployeesByDepartment(String deptName) {
        return employeeRepository.findByDeptName(deptName);
    }


}