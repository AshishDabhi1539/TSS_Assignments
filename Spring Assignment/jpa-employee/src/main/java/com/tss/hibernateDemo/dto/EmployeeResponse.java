package com.tss.hibernateDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

	private int empId;
    private String name;
    private double salary;
    private SalaryAccountResponse salaryAccount;
}
