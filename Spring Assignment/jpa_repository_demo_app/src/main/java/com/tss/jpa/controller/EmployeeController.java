package com.tss.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.jpa.entity.Employee;
import com.tss.jpa.service.EmployeeService;

@RestController
@RequestMapping("/employeeapp")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;
    
    @GetMapping("/readAll")
    public List<Employee> readAllEmployee()
    {
        return employeeService.readAllEmployee();
    }
    
    @PostMapping("/addNew")
    public Employee addNewEmployee(@RequestBody Employee employee)
    {
        return employeeService.addNewEmployee(employee);
    }
    
    @GetMapping("/find/{id}")
    public Employee findEmployeeById(@PathVariable int id) {
        return employeeService.findEmployeeById(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteEmployeeById(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }
    
    @GetMapping("/department/{deptName}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String deptName) {
        return employeeService.findEmployeesByDepartment(deptName);
    }
}