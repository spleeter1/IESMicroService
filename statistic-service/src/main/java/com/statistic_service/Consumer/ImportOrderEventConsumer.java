package com.statistic_service.Consumer;

import com.statistic_service.Event.ImportOrderCreatedEvent;
import com.statistic_service.Service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImportOrderEventConsumer {
    private StatisticService statisticService;
    @KafkaListener(topics = "importOrder.created",groupId = "import-order-service-group")
    public void handleImportOrderCreated(ImportOrderCreatedEvent event){
        statisticService.updateSupplierImportStats(event);
    }
}
