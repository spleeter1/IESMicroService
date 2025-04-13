package com.order_service.Controller;

import com.order_service.DTO.OrderRequestDTO;
import com.order_service.DTO.OrderResponseDTO;
import com.order_service.Enums.OrderStatus;
import com.order_service.Enums.UserRole;
import com.order_service.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody  OrderRequestDTO requests){
        try{
            OrderResponseDTO orderResponseDTO= orderService.createOrder(requests);
            return ResponseEntity.ok(orderResponseDTO);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<?> updateOrderStatus(  @PathVariable Long orderId,
                                                 @RequestParam OrderStatus nextStatus,
                                                 @RequestHeader("X-Role") UserRole role){
        try{
            OrderResponseDTO  orderResponseDTO =orderService.updateOrderStatus(orderId,nextStatus,role);
            return ResponseEntity.ok(orderResponseDTO);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
