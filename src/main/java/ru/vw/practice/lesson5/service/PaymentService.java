package ru.vw.practice.lesson5.service;

import jakarta.transaction.Transactional;
import ru.vw.practice.lesson6.dto.PaymentRequest;
import ru.vw.practice.lesson6.dto.ProductDto;

public interface PaymentService {
  @Transactional
  ProductDto executePayment(PaymentRequest request);
}
