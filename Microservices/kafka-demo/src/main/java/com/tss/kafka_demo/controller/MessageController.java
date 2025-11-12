package com.tss.kafka_demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tss.kafka_demo.kafka.KafkaProducer;

@RestController
@RequestMapping("/api/kafka")
public class MessageController {
	
	private KafkaProducer kafkaProducer;

	public MessageController(KafkaProducer kafkaProducer) {
		super();
		this.kafkaProducer = kafkaProducer;
	}
	
	@GetMapping("/message")
	public ResponseEntity<String> publish(@RequestParam String message){
		kafkaProducer.sendMessage(message);
		
		return ResponseEntity.ok("Message published ----------> " + message);
	}
	
}
