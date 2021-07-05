package com.gspe.sale.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DeliveryDto {
    private Long id;
    private String name;
    private String billTo;
    private String billContact;
    private PurchaseOrderDto purchaseOrderDto;
    private List<ProductDto> products;
}
