package com.order_service.Event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class OrderCreatedEvent {
    private long agentId;
    private String agentName;
    private long totalAmount;
    private LocalDate orderDate;
    private List<OrderDetailCreatedEvent> orderDetailCreatedEventList;

}
