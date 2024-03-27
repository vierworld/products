package ru.vw.practice.lesson5.service;

import ru.vw.practice.lesson.dto.ProductDto;
import ru.vw.practice.lesson.dto.ProductsInfoResponse;

import java.util.Optional;

public interface ProductsService {
  ProductsInfoResponse getByUserId(long userId);

  Optional<ProductDto> getByProductId(long productId);
}
