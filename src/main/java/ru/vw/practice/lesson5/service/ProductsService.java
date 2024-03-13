package ru.vw.practice.lesson5.service;

import ru.vw.practice.lesson5.dto.PaymentRequest;
import ru.vw.practice.lesson5.dto.Product;
import ru.vw.practice.lesson5.dto.ProductsInfoResponse;

import java.util.Optional;

public interface ProductsService {
  ProductsInfoResponse getByUserId(long userId);

  Optional<Product> getByProductId(long productId);

  Optional<Product> executePayment(PaymentRequest request);
}
