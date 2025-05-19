package com.import_order_service.Controller;

import com.import_order_service.Model.ImportOrder;
import com.import_order_service.Service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/import")
@RequiredArgsConstructor
public class ImportOrderController {
    private final ImportOrderService importOrderService;

    @GetMapping("/check")
    public ResponseEntity<?> home() {
        return ResponseEntity.ok("ImportOrderService is working!");
    }

    @PostMapping("/create")
    public ResponseEntity<?> createImportOrder(@RequestBody ImportOrder importOrder) {
        try {
            ImportOrder saved = importOrderService.createOrder(importOrder);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
