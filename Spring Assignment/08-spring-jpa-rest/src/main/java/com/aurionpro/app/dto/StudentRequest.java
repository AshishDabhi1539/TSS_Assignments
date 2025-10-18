package com.aurionpro.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class StudentRequest {
	@NotBlank(message = "Name is required")
	private String name;

	@Email(message = "Email must be valid")
	private String email;

	private Integer age;

	public StudentRequest() {
	}

	public StudentRequest(String name, String email, Integer age) {
		this.name = name;
		this.email = email;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "StudentRequest [name=" + name + ", email=" + email + ", age=" + age + "]";
	}

}
