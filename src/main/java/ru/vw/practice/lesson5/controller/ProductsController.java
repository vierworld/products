package ru.vw.practice.lesson5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vw.practice.lesson6.dto.ProductDto;
import ru.vw.practice.lesson5.exception.CustomException;
import ru.vw.practice.lesson5.service.ProductsService;
import ru.vw.practice.lesson6.dto.ProductsInfoResponse;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
public class ProductsController {
  private final ProductsService productsService;

  public ProductsController(ProductsService productsService) {
    this.productsService = productsService;
  }

  @GetMapping("/{productId}")
  public ProductDto getProductById(@PathVariable long productId) {
    Optional<ProductDto> result = productsService.getByProductId(productId);
    return result.orElseThrow(() -> new CustomException("Недостаточно ресурсов", CustomException.ErrorCodes.NOT_FOUND));
  }

  @GetMapping()
  public ProductsInfoResponse getProductsByUserId(@RequestHeader(value = "userId") long userId) {
    return productsService.getByUserId(userId);
  }

}
