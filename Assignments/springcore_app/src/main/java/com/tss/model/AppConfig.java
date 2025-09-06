package com.tss.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Bean
	public Department department() {
		return new Department("Computer Engineering");
	}

	@Bean
	public Employee employee() {
		return new Employee("Ashish", department());
	}
}
