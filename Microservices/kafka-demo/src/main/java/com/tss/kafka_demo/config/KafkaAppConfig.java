package com.tss.kafka_demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaAppConfig {

	@Bean
	NewTopic myTopic() {
		return TopicBuilder.name("myTopic").build();
	}
	
}
