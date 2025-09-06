package com.tss.hibernateDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.hibernateDemo.entity.SalaryAccount;

public interface SalaryAccountRepository extends JpaRepository<SalaryAccount, Integer>{

}
