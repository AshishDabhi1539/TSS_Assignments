package com.tss.department_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tss.department_service.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
