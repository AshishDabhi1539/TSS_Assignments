package com.tss.hibernateDemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="salary_account")
@Data
public class SalaryAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "acc_id")
	private int accountId;
	@Column (name="acc_no")
	private long accountNumber;
	@Column(name="bank_name")
	private String bankName;
	@Column(name="branch")
	private String branch;
	@Column(name="ifsc_code")
	private String ifscCode;
	
	
}
