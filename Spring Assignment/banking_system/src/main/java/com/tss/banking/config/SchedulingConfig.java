package com.tss.banking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.tss.banking.service.OtpService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableScheduling
@Slf4j
public class SchedulingConfig {

    @Autowired
    private OtpService otpService;

    @Scheduled(fixedRate = 3600000) // Run every hour (3600000 ms)
    public void cleanupExpiredOtpTokens() {
        log.info("Starting scheduled cleanup of expired OTP tokens");
        try {
            otpService.cleanupExpiredTokens();
            log.info("Completed scheduled cleanup of expired OTP tokens");
        } catch (Exception e) {
            log.error("Error during scheduled OTP token cleanup", e);
        }
    }
}
