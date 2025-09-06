package com.tss.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.jpa.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    List<Employee> findByDeptName(String deptName);
}