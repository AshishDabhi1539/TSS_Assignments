package com.tss.ioc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tss.ioc.entity.Computer;
import com.tss.ioc.entity.Department;
import com.tss.ioc.entity.Employee;
import com.tss.ioc.entity.Harddisk;

@Configuration
public class AppConfig {

	@Bean
	Computer compute() {
		return new Computer();
	}
	
	@Bean
	Harddisk harddisk() {
		return new Harddisk();
	}
	
	@Bean
    Employee employee() {
        return new Employee();
    }

    @Bean
    Department department() {
        return new Department();
    }
}
