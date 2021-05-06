package com.kuroshan.workshop.ms.dummy.controllers.advice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kuroshan.workshop.ms.dummy.controllers.response.ErrorValidationRequest;

@RestControllerAdvice
public class ErrorControllerAdvice extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers, HttpStatus status,
                                                                WebRequest request) {
    ErrorValidationRequest error = new ErrorValidationRequest(HttpStatus.BAD_REQUEST, "Validation Error", ex.getBindingResult().toString());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorValidationRequest> handleConstraintViolation(ConstraintViolationException ex){
	  ErrorValidationRequest error = new ErrorValidationRequest(HttpStatus.NOT_FOUND, "Constraint violation", ex.getMessage());
	  return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorValidationRequest> handleEntityNotFound(EntityNotFoundException ex){
    ErrorValidationRequest error = new ErrorValidationRequest(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

}
