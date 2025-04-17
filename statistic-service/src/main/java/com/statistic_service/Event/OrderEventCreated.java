package com.statistic_service.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEventCreated {
    private long agentId;
    private String agentName;
    private long totalAmount;
    private LocalDate orderDate;
    private List<OrderDetailCreatedEvent> orderDetailCreatedEventList;

}
