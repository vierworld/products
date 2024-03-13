package ru.vw.practice.lesson5.dto;

import ru.vw.practice.lesson5.exception.CustomException;

public class ErrorResponse {
  private String errorMessage;
  private CustomException.ErrorCodes errorCode;

  public ErrorResponse(String errorMessage, CustomException.ErrorCodes errorCode) {
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public CustomException.ErrorCodes getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(CustomException.ErrorCodes errorCode) {
    this.errorCode = errorCode;
  }
}
