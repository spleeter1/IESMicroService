package com.statistic_service.Controller;

import com.statistic_service.Service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping("/time-revenue")
    public ResponseEntity<?> getTimeRevenueStats(){
        try {
            return ResponseEntity.ok().body(statisticService.getAllTimeRevenueStats());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/supplier-import")
    public ResponseEntity<?> getSupplierImportStats(){
        try {
            return ResponseEntity.ok().body(statisticService.getAllSupplierImportStats());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/agent-revenue")
    public ResponseEntity<?> getAgentRevenueStats(){
        try {
            return ResponseEntity.ok().body(statisticService.getAllAgentRevenueStats());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/product-revenue")
    public ResponseEntity<?> getProductRevenueStats(){
        try {
            return ResponseEntity.ok().body(statisticService.getAllProductRevenueStats());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
