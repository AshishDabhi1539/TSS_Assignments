package com.tss.ioc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.ioc.entity.Department;

@RestController
@RequestMapping("/app")
public class DepartmentController {

    @Autowired
    private Department department;

    @GetMapping("/department")
    public Department getDepartment() {
        return department;
    }
}