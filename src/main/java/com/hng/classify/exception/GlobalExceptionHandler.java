package com.hng.classify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MissingServletRequestParameterException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
    ErrorResponse error = new ErrorResponse("error", "The 'name' parameter is required");
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeExceptions(RuntimeException ex) {
    ErrorResponse error = new ErrorResponse("error", ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
  }

}
