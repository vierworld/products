package ru.vw.practice.lesson5.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.vw.practice.lesson.dto.PaymentRequest;
import ru.vw.practice.lesson.dto.ProductDto;
import ru.vw.practice.lesson5.validator.DtoValidatorService;
import ru.vw.practice.lesson5.entity.ProductEntity;
import ru.vw.practice.lesson.exception.CustomException;
import ru.vw.practice.lesson5.repository.ProductsRepository;
import ru.vw.practice.lesson5.service.PaymentService;
import ru.vw.practice.lesson5.utils.ProductMapperUtils;

import java.util.Comparator;
import java.util.Objects;

@Service
public class PaymentServiceImpl implements PaymentService {
  private final DtoValidatorService validator;
  private final ProductsRepository productsRepository;
  public PaymentServiceImpl(DtoValidatorService validator, ProductsRepository productsRepository) {
    this.validator = validator;
    this.productsRepository = productsRepository;
  }

  @Override
  @Transactional
  public ProductDto executePayment(PaymentRequest request) {
    validator.checkValidationErrors(request);

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
