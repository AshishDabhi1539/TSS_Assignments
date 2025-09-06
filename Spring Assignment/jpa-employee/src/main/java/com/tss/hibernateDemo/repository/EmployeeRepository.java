package com.tss.hibernateDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.hibernateDemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
