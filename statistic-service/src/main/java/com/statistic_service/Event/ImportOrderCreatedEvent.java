package com.statistic_service.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImportOrderCreatedEvent {
        private long supplierId;
        private int totalQuantity;
}
