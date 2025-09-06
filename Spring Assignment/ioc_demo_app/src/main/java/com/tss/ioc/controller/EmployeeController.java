package com.tss.ioc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.ioc.entity.Employee;

@RestController
@RequestMapping("/app")
public class EmployeeController {

    @Autowired
    private Employee employee;

    @GetMapping("/employee")
    public Employee getEmployee() {
        return employee;
    }
}