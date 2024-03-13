package ru.vw.practice.lesson5.entity;

import java.math.BigDecimal;

public class ProductEntity {
  private Long productId;
  private Long userId;
  private String billNumber;
  private BigDecimal balance;
  private Integer productType;
  public ProductEntity() {
  }

  public ProductEntity(Long productId,
                       Long userId,
                       String billNumber,
                       BigDecimal balance,
                       Integer productType) {
    this.productId = productId;
    this.userId = userId;
    this.billNumber = billNumber;
    this.balance = balance;
    this.productType = productType;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getBillNumber() {
    return billNumber;
  }

  public void setBillNumber(String billNumber) {
    this.billNumber = billNumber;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Integer getProductType() {
    return productType;
  }

  public void setProductType(Integer productType) {
    this.productType = productType;
  }

  public static ProductBuilder builder() {
    return new ProductBuilder();
  }

  public static class ProductBuilder {
    private Long productId;
    private Long userId;
    private String billNumber;
    private BigDecimal balance;
    private Integer productType;

    public ProductBuilder productId(Long productId) {
      this.productId = productId;
      return this;
    }

    public ProductBuilder userId(Long userId) {
      this.userId = userId;
      return this;
    }

    public ProductBuilder billNumber(String billNumber) {
      this.billNumber = billNumber;
      return this;
    }

    public ProductBuilder balance(BigDecimal balance) {
      this.balance = balance;
      return this;
    }

    public ProductBuilder productType(int productType) {
      this.productType = productType;
      return this;
    }


    public ProductEntity build() {
      return new ProductEntity(productId,
              userId,
              billNumber,
              balance,
              productType);
    }
  }
}
