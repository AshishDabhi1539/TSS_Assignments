package com.tss.policy.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class InsurancePolicyRequestDto {

	@NotBlank(message = "Holder name is required")
	@Size(min = 2, max = 100, message = "Holder name must be between 2 and 100 characters")
	private String holderName;

	@NotNull(message = "Start date is required")
	private LocalDate startDate;

	@NotNull(message = "End date is required")
	@Future(message = "End date must be in the future")
	private LocalDate endDate;

	@NotNull(message = "Amount is required")
	@Positive(message = "Amount must be greater than 0")
	private Double amount;
}
