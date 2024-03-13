package ru.vw.practice.lesson5.repository;

import ru.vw.practice.lesson5.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository {
  List<ProductEntity> getByUserId(long userId);

  Optional<ProductEntity> getByProductId(long productId);
}
