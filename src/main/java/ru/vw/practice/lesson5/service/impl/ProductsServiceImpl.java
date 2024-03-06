package ru.vw.practice.lesson5.service.impl;

import org.springframework.stereotype.Service;
import ru.vw.practice.lesson5.dto.PaymentRequest;
import ru.vw.practice.lesson5.dto.Product;
import ru.vw.practice.lesson5.dto.ProductsInfoResponse;
import ru.vw.practice.lesson5.exception.CustomException;
import ru.vw.practice.lesson5.repository.ProductsRepository;
import ru.vw.practice.lesson5.service.ProductsService;

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
    return new ProductsInfoResponse(productsRepository.getByUserId(userId));
  }

  @Override
  public Optional<Product> getByProductId(long productId) {
    return productsRepository.getByProductId(productId);
  }

  @Override
  public Optional<Product> executePayment(PaymentRequest request) {
    Optional<Product> product = productsRepository.getByProductId(request.getProductId());
    product.ifPresent(a-> {
      if (Objects.compare(a.getBalance(), request.getAmount(), Comparator.naturalOrder()) > 0) {
        a.setBalance(a.getBalance().subtract(request.getAmount()));
      } else {
        throw new CustomException("Недостаточно средств", CustomException.ErrorCodes.NOT_ENOUGH_RESOURCES);
      }
    });
    return product;
  }

}
