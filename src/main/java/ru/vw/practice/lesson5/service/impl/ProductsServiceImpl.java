package ru.vw.practice.lesson5.service.impl;

import org.springframework.stereotype.Service;
import ru.vw.practice.lesson5.dto.PaymentRequest;
import ru.vw.practice.lesson5.entity.ProductEntity;
import ru.vw.practice.lesson5.utils.ProductMapperUtils;
import ru.vw.practice.lesson6.dto.Product;
import ru.vw.practice.lesson5.dto.ProductsInfoResponse;
import ru.vw.practice.lesson5.exception.CustomException;
import ru.vw.practice.lesson5.repository.ProductsRepository;
import ru.vw.practice.lesson5.service.ProductsService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {
  private final ProductsRepository productsRepository;

  public ProductsServiceImpl(ProductsRepository productsRepository) {
    this.productsRepository = productsRepository;
  }

  @Override
  public ProductsInfoResponse getByUserId(long userId) {
    return new ProductsInfoResponse(ProductMapperUtils.mapProductEntityListToProductList(productsRepository.getByUserId(userId)));
  }

  @Override
  public Optional<Product> getByProductId(long productId) {
    return productsRepository.getByProductId(productId).map(ProductMapperUtils::mapProductEntityToProduct);
  }

  @Override
  public Optional<Product> executePayment(PaymentRequest request) {
    if (Objects.isNull(request.getAmount()) ||
            (Objects.compare(request.getAmount(), BigDecimal.ZERO, Comparator.naturalOrder()) <= 0)) {
      throw new CustomException("Невалидный входной параметр: amount должен быть больше нуля",
              CustomException.ErrorCodes.INVALID_INPUT);
    }

    Optional<ProductEntity> product = productsRepository.getByProductId(request.getProductId());

    if (product.isEmpty()) {
      throw new CustomException("Отсутствует заданный продукт",
              CustomException.ErrorCodes.INVALID_INPUT);
    }

    product.ifPresent(a -> {
      if (Objects.compare(a.getBalance(), request.getAmount(), Comparator.naturalOrder()) >= 0) {
        a.setBalance(a.getBalance().subtract(request.getAmount()));
      } else {
        throw new CustomException("Недостаточно средств", CustomException.ErrorCodes.NOT_ENOUGH_RESOURCES);
      }
    });
    return product.map(ProductMapperUtils::mapProductEntityToProduct);
  }

}
