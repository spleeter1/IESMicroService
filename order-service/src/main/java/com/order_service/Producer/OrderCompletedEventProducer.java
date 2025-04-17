package com.order_service.Producer;

import com.order_service.Event.OrderCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class OrderCompletedEventProducer {
    private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public void send(OrderCreatedEvent e){
        kafkaTemplate.send("order.completed",e);
    }
}
