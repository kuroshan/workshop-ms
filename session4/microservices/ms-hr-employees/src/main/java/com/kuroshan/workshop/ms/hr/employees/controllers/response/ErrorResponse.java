package com.kuroshan.workshop.ms.hr.employees.controllers.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class ErrorResponse {

	private HttpStatus httpStatus;
	private LocalDateTime timestamp;
	private String message;
	private String details;

	public ErrorResponse(HttpStatus httpStatus, String message, String details) {
		this.httpStatus = httpStatus;
		this.timestamp = LocalDateTime.now();
		this.message = message;
		this.details = details;
	}
}
