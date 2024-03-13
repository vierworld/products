package ru.vw.practice.lesson5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vw.practice.lesson5.dto.PaymentRequest;
import ru.vw.practice.lesson6.dto.Product;
import ru.vw.practice.lesson5.exception.CustomException;
import ru.vw.practice.lesson5.service.ProductsService;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {
  private final ProductsService productsService;

  public ProductsController(ProductsService productsService) {
    this.productsService = productsService;
  }

  @GetMapping("/{productId}")
  public Product getProductById(@PathVariable long productId) {
    Optional<Product> result = productsService.getByProductId(productId);
    return result.orElseThrow(()->new CustomException("Недостаточно ресурсов", CustomException.ErrorCodes.NOT_FOUND));
  }

  @PostMapping("/exec")
  public Product getProductsByUserId(@RequestBody PaymentRequest request) {
    Optional<Product> result = productsService.executePayment(request);
    return result.orElseThrow(()->new CustomException("Недостаточно ресурсов", CustomException.ErrorCodes.NOT_FOUND));
  }

}
