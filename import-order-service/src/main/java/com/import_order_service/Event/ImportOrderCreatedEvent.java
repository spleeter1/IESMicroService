package com.import_order_service.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportOrderCreatedEvent {
    private long supplierId;
    private String supplierName;
    private int totalQuantity;
}
