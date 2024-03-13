package ru.vw.practice.lesson5.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
  private ErrorCodes code;
  private HttpStatus httpStatus;
  public CustomException(String message, ErrorCodes code) {
    super(message);
    this.code = code;
    this.httpStatus = code.httpStatus;
  }

  public ErrorCodes getCode() {
    return code;
  }

  public void setCode(ErrorCodes code) {
    this.code = code;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public enum ErrorCodes {
      NOT_FOUND(HttpStatus.NOT_FOUND),
      NOT_ENOUGH_RESOURCES(HttpStatus.CONFLICT),
      INVALID_INPUT(HttpStatus.CONFLICT);

      private HttpStatus httpStatus;
      ErrorCodes(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
      }
    }
}
