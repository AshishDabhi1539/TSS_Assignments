package com.tss.department_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	@Bean
    WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
