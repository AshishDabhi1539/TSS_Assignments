package com.tss.jpa.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class ResponseError {
	private int status;
	private long timestamp;
	private String message;
}
