package com.kuroshan.workshop.ms.hr.users.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kuroshan.workshop.ms.hr.users.controllers.response.ErrorResponse;
import com.kuroshan.workshop.ms.hr.users.exceptions.UserException;

@RestControllerAdvice
public class ErrorControllerAdvice {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorResponse> handleAll(UserException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error General", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
