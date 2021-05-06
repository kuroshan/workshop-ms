package com.kuroshan.workshop.ms.dummy.controllers.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class ErrorValidationRequest {

  private HttpStatus httpStatus;

  private LocalDateTime timestamp;

  private String message;

  private String details;

  public ErrorValidationRequest(HttpStatus httpStatus, String message, String details) {
    this.httpStatus = httpStatus;
    this.timestamp = LocalDateTime.now();
    this.message = message;
    this.details = details;
  }

}
