package com.gspe.sale.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class PurchaseOrderDto {
    private Long id;
    private String name;
    private String address;
    private String salesName;
    private String site;
    private String materialDocumentNumber;
    private String currency;
    private String billTo;
    private String billContact;
    private BigDecimal total;
    List<ProductDto> products;
}
