package com.product.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductReserveRequest {
    private Long productId;
    private int quantity;
}

