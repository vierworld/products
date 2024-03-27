package ru.vw.practice.lesson5.validator;

public interface DtoValidatorService {
  <T> void checkValidationErrors(T item);
}
