package com.import_order_service.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImportOrderRequest {
    private long supplierId;
    private String supplierName;
    private List<ImportOrderDetailRequest> orderDetails;
}
