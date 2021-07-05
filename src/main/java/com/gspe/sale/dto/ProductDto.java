package com.gspe.sale.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String productDetail;
    private String type;
    private String unitMeasure;
    private BigDecimal unitPrice;
    private int quantity;
    private int discount;
    private BigDecimal discountAmount;
    private BigDecimal total;
    private String lineItem;
    private String poNumber;
    private String doNumber;
}
