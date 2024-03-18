package ru.vw.practice.lesson5.service.impl;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import ru.vw.practice.lesson5.entity.ProductEntity;
import ru.vw.practice.lesson5.exception.CustomException;
import ru.vw.practice.lesson5.repository.ProductsRepository;
import ru.vw.practice.lesson5.service.PaymentService;
import ru.vw.practice.lesson5.utils.ProductMapperUtils;
import ru.vw.practice.lesson6.dto.PaymentRequest;
import ru.vw.practice.lesson6.dto.ProductDto;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
  private final Validator validator;
  private final ProductsRepository productsRepository;
  public PaymentServiceImpl(Validator validator, ProductsRepository productsRepository) {
    this.validator = validator;
    this.productsRepository = productsRepository;
  }

  @Override
  @Transactional
  public ProductDto executePayment(PaymentRequest request) {
    Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

    if (Objects.isNull(request.getAmount()) || !violations.isEmpty()) {
      throw new CustomException("Невалидный входной параметр: %s".formatted(violations.stream()
              .map(ConstraintViolation::getMessage).collect(Collectors.joining("; "))),
              CustomException.ErrorCodes.INVALID_INPUT);
    }

    ProductEntity product = productsRepository.findById(request.getProductId()).orElseThrow(() -> new CustomException("Отсутствует заданный продукт",
            CustomException.ErrorCodes.INVALID_INPUT));
    if (Objects.compare(product.getBalance(), request.getAmount(), Comparator.naturalOrder()) < 0) {
      throw new CustomException("Недостаточно средств", CustomException.ErrorCodes.NOT_ENOUGH_RESOURCES);
    }
    product.setBalance(product.getBalance().subtract(request.getAmount()));
    productsRepository.save(product);
    return ProductMapperUtils.mapProductEntityToProductDto(product);
  }
}
