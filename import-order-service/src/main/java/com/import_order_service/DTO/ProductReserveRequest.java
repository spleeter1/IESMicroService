package com.import_order_service.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReserveRequest {
    private Long productId;
    private int quantity;
}