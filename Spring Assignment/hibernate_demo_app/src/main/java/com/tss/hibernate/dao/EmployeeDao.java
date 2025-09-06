package com.tss.hibernate.dao;

import java.util.List;

import com.tss.hibernate.entity.Employee;

public interface EmployeeDao {
	Employee addNewEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee findEmployeeById(int id);
    Employee getEmployeeByName(String name);
    List<Employee> getEmployeesByDeptName(String deptName);
}
