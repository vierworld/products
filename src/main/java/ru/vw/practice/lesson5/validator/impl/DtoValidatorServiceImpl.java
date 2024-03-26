package ru.vw.practice.lesson5.validator.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import ru.vw.practice.lesson.dto.ValidationFlagsDto;
import ru.vw.practice.lesson.exception.CustomDetailedException;
import ru.vw.practice.lesson.exception.CustomException;
import ru.vw.practice.lesson5.validator.DtoValidatorService;

import java.util.Set;

@Service
public class DtoValidatorServiceImpl implements DtoValidatorService {
  private final Validator validator;

  public DtoValidatorServiceImpl(Validator validator) {
    this.validator = validator;
  }

  @Override
  public <T> void checkValidationErrors(T item) {
    Set<ConstraintViolation<T>> violations = validator.validate(item);

    if (!violations.isEmpty()) {
      throw new CustomDetailedException(
              "Валидация входного параметра не пройдена",
              CustomException.ErrorCodes.INVALID_INPUT,
              violations.stream()
                      .map(a->new ValidationFlagsDto(a.getPropertyPath().toString(), a.getMessage())).toList()
      );
    }
  }
}
