package com.order_service.DTO;

import com.order_service.Enums.OrderStatus;
import com.order_service.model.Order;
import com.order_service.model.OrderDetail;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderResponseDTO {
    private long id;
    private String agentName;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private long totalAmount;
    private List<OrderDetailResponseDTO> details;

    public OrderResponseDTO(Order order){
        this.id = order.getId();
        this.agentName = order.getAgentName();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getOrderStatus();
        this.totalAmount = order.getTotalAmount();
        this.details = new ArrayList<>();

        for (OrderDetail detail : order.getOrderDetailList()){
            details.add(new OrderDetailResponseDTO(detail));
        }
    }
}
