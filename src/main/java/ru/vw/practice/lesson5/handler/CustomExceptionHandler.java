package ru.vw.practice.lesson5.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.vw.practice.lesson5.dto.ErrorResponse;
import ru.vw.practice.lesson5.exception.CustomException;

@ControllerAdvice
public class CustomExceptionHandler {
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
    return new ResponseEntity<>(new ErrorResponse(e.getMessage(), e.getCode()), e.getHttpStatus());
  }
}