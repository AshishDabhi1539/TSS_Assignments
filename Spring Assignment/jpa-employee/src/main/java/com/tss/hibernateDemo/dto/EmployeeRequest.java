package com.tss.hibernateDemo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

	@NotBlank(message = "Name is required")
	private String name;

	@NotNull(message = "Salary is required")
	@PositiveOrZero(message = "Salary must be greater than or equal to 0")
	private Double salary;

	@Valid
	@NotNull(message = "Salary account is required")
	private SalaryAccountRequest salaryAccount;
}