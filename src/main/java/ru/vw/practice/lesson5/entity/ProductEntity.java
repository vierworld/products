package ru.vw.practice.lesson5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;


@Entity
@Table(name = "product", schema = "csep")
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id")
  private Long productId;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "bill_number")
  private String billNumber;

  @Column(name = "balance")
  private BigDecimal balance;

  @Column(name = "product_type")
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
