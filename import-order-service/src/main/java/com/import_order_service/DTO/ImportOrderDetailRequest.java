package com.import_order_service.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportOrderDetailRequest {
    private long productId;
    private String productName;
    private long unitPrice;
    private int quantity;
}
