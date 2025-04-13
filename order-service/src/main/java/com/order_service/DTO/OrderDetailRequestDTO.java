package com.order_service.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDetailRequestDTO {
    private long productId;
    private String productName;
    private int quantity;
    private long productSalePrice;
}
