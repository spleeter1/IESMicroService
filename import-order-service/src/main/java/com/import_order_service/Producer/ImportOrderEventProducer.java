package com.import_order_service.Producer;

import com.import_order_service.Event.ImportOrderCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImportOrderEventProducer {
    private final KafkaTemplate<String, ImportOrderCreatedEvent> kafkaTemplate;

    public void send(ImportOrderCreatedEvent event){
        kafkaTemplate.send("import-order.created",event);
    }
}
