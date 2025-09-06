package com.tss.hibernate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tss.hibernate.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private EntityManager manager;

    @Override
    @Transactional
    public Employee addNewEmployee(Employee employee) {
        return manager.merge(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        TypedQuery<Employee> query = manager.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findEmployeeById(int id) {
        return manager.find(Employee.class, id);
    }

    @Override
    public Employee getEmployeeByName(String name) {
        TypedQuery<Employee> query = manager.createQuery(
                "SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<Employee> getEmployeesByDeptName(String deptName) {
        TypedQuery<Employee> query = manager.createQuery(
                "SELECT e FROM Employee e WHERE e.deptName = :deptName", Employee.class);
        query.setParameter("deptName", deptName);
        return query.getResultList();
    }
}