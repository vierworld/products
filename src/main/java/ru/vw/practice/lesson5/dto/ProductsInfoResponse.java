package ru.vw.practice.lesson5.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.CollectionUtils;
import ru.vw.practice.lesson6.dto.Product;

import java.util.List;

public class ProductsInfoResponse {
  private final List<Product> products;

  public ProductsInfoResponse(List<Product> products) {
    this.products = products;
  }

  public List<Product> getProducts() {
    return products;
  }

  @JsonIgnore
  public boolean isEmpty() {
    return CollectionUtils.isEmpty(products);
  }

}
