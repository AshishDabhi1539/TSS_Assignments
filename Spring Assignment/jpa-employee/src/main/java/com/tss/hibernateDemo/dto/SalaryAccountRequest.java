package com.tss.hibernateDemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SalaryAccountRequest {

	@NotNull(message = "Account number is required")
	private Long accountNumber;

	@NotBlank(message = "Bank name is required")
	private String bankName;

	@NotBlank(message = "Branch is required")
	private String branch;

	@NotBlank(message = "IFSC code is required")
	private String ifscCode;

}
