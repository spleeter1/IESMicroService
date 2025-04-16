package com.order_service.Event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class OrderCreatedEvent {

}
