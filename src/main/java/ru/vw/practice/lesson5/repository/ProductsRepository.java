package ru.vw.practice.lesson5.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.vw.practice.lesson5.entity.ProductEntity;

import java.util.List;

public interface ProductsRepository extends ListCrudRepository<ProductEntity, Long> {
  List<ProductEntity> getByUserId(long userId);
}
