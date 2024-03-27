package ru.vw.practice.lesson5.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.vw.practice.lesson.dto.ErrorDetailedResponse;
import ru.vw.practice.lesson.dto.ErrorResponse;
import ru.vw.practice.lesson.exception.CustomDetailedException;
import ru.vw.practice.lesson.exception.CustomException;

import java.time.OffsetDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(CustomDetailedException.class)
  public ResponseEntity<ErrorDetailedResponse> handleCustomDetailedException(CustomDetailedException e) {

    return new ResponseEntity<>(new ErrorDetailedResponse(e.getMessage(), e.getCode().toString(), OffsetDateTime.now(), e.getValidationFlags()), e.getHttpStatus());
  }

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {

    return new ResponseEntity<>(new ErrorResponse(e.getMessage(), e.getCode().toString(), OffsetDateTime.now()), e.getHttpStatus());
  }
}