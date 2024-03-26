package ru.vw.practice.lesson5.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vw.practice.lesson.dto.PaymentRequest;
import ru.vw.practice.lesson.dto.ProductDto;
import ru.vw.practice.lesson5.service.PaymentService;


@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
  private final PaymentService paymentService;

  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping("/exec")
  public ProductDto executePayment(@RequestBody PaymentRequest request) {
    return paymentService.executePayment(request);
  }

}
