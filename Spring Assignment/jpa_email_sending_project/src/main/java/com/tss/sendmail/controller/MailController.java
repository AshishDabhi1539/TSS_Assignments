package com.tss.sendmail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.sendmail.entity.Mail;
import com.tss.sendmail.service.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private MailService mailService;

	@PostMapping("/send")
	public ResponseEntity<String> sendEmail(@RequestBody Mail mail) {
		try {
			mailService.sendEmail(mail);
			return ResponseEntity.ok("Email sent successfully to " + mail.getMailTo());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
		}
	}
}
