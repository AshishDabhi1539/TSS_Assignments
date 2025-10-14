package com.tss.storedocx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {

	@Bean
	Cloudinary cloudinary() {
		return new Cloudinary(
				ObjectUtils.asMap("cloud_name", System.getProperty("CloudName", System.getenv("CloudName")), "api_key",
						System.getProperty("APIkey", System.getenv("APIkey")), "api_secret",
						System.getProperty("Secret", System.getenv("Secret"))));
	}
}
