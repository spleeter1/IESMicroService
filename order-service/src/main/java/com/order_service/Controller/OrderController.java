package com.order_service.Controller;

import com.order_service.DTO.OrderRequestDTO;
import com.order_service.DTO.OrderResponseDTO;
import com.order_service.Service.OrderService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/agent_order")
    public ResponseEntity<?> createOrder(@RequestBody  OrderRequestDTO requests){
        try{
            OrderResponseDTO orderResponseDTO= orderService.createOrder(requests);
            return ResponseEntity.ok(orderResponseDTO);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: "+e.getMessage());
        }
    }
}
