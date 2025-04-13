package com.import_order_service.Controller;

import com.import_order_service.DTO.ImportOrderRequest;
import com.import_order_service.DTO.ImportOrderResponse;
import com.import_order_service.Service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class ImportOrderController {
    private final ImportOrderService importOrderService;

    @GetMapping("/check")
    public ResponseEntity<?> home(){
        return ResponseEntity.ok("ImportOrderService is working!");
    }
    @PostMapping("/import")
    public ResponseEntity<?> createImportOrder(@RequestBody ImportOrderRequest requests){
        try{
            ImportOrderResponse importOrderResponse = importOrderService.createOrder(requests);
            return ResponseEntity.ok(importOrderResponse);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
