package com.tss.banking.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/auth/register").permitAll()
                .requestMatchers("/customer/**").hasRole("CUSTOMER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/superadmin/**").hasRole("SUPERADMIN")
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginProcessingUrl("/auth/login")
                .permitAll()
            )
            .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails customer = User.withDefaultPasswordEncoder()
            .username("customer")
            .password("password")
            .roles("CUSTOMER")
            .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("password")
            .roles("ADMIN")
            .build();
        UserDetails superadmin = User.withDefaultPasswordEncoder()
            .username("superadmin")
            .password("password")
            .roles("SUPERADMIN")
            .build();
        return new InMemoryUserDetailsManager(customer, admin, superadmin);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}