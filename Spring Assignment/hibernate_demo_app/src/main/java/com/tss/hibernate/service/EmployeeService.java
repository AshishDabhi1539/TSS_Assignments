package com.tss.hibernate.service;

import java.util.List;

import com.tss.hibernate.entity.Employee;

public interface EmployeeService {
    Employee addNewEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    Employee getEmployeeByName(String name);
    List<Employee> getEmployeesByDeptName(String deptName);
}