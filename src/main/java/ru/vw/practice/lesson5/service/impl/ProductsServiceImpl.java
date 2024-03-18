package ru.vw.practice.lesson5.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.vw.practice.lesson5.utils.ProductMapperUtils;
import ru.vw.practice.lesson6.dto.ProductDto;
import ru.vw.practice.lesson6.dto.ProductsInfoResponse;
import ru.vw.practice.lesson5.repository.ProductsRepository;
import ru.vw.practice.lesson5.service.ProductsService;

import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {
  private final ProductsRepository productsRepository;

  public ProductsServiceImpl(ProductsRepository productsRepository) {
    this.productsRepository = productsRepository;
  }

  @Override
  @Transactional
  public ProductsInfoResponse getByUserId(long userId) {
    return new ProductsInfoResponse(ProductMapperUtils.mapProductEntityListToProductDtoList(productsRepository.getByUserId(userId)));
  }

  @Override
  @Transactional
  public Optional<ProductDto> getByProductId(long productId) {
    return productsRepository.findById(productId).map(ProductMapperUtils::mapProductEntityToProductDto);
  }

}
