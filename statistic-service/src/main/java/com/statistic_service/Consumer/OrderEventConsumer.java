package com.statistic_service.Consumer;

import com.statistic_service.Event.OrderEventCreated;
import com.statistic_service.Service.StatisticService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderEventConsumer {
    private final StatisticService statisticService;
    @KafkaListener(topics = "order.completed",groupId = "order-service-group")
    public void handleCompletedOrder(OrderEventCreated e){
        statisticService.handleFullOrderStats(e);
    }
}
