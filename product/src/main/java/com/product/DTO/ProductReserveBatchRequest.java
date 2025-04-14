package com.product.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductReserveBatchRequest {
    List<ProductReserveRequest> items;
}
