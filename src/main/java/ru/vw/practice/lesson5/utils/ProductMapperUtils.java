package ru.vw.practice.lesson5.utils;

import org.springframework.util.CollectionUtils;
import ru.vw.practice.lesson5.entity.ProductEntity;
import ru.vw.practice.lesson6.dto.Product;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ProductMapperUtils {
  public static Product mapProductEntityToProduct(ProductEntity productEntity) {
    if (Objects.isNull(productEntity)) {
      return null;
    }
    return Product.builder().productId(productEntity.getProductId())
            .productType(Product.ProductType.getByCode(productEntity.getProductType()))
            .balance(productEntity.getBalance())
            .userId(productEntity.getUserId())
            .billNumber(productEntity.getBillNumber())
            .build();
  }

  public static List<Product> mapProductEntityListToProductList(List<ProductEntity> productEntity) {
    if (CollectionUtils.isEmpty(productEntity)) {
      return Collections.emptyList();
    }
    return productEntity.stream().map(ProductMapperUtils::mapProductEntityToProduct).toList();
  }

}
