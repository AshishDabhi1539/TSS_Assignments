package com.tss.employee_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
	
	@Bean
	WebClient webClient() {
		return WebClient.builder().build();
	}
}
