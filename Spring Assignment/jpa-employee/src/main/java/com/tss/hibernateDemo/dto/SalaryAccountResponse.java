package com.tss.hibernateDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryAccountResponse {

	private int accountId;
    private long accountNumber;
    private String bankName;
    private String branch;
    private String ifscCode;
}
