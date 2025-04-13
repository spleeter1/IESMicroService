package com.order_service.Service;

import com.order_service.DTO.OrderDetailRequestDTO;
import com.order_service.DTO.OrderRequestDTO;
import com.order_service.DTO.OrderResponseDTO;
import com.order_service.Enums.OrderStatus;
import com.order_service.Enums.SourceType;
import com.order_service.Enums.UserRole;
import com.order_service.Repository.OrderRepository;
import com.order_service.model.Order;
import com.order_service.model.OrderDetail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderFlowService orderFlowService;

    public OrderResponseDTO createOrder (OrderRequestDTO requests){
        Order order = new Order();
        order.setAgentId(requests.getAgentId());
        order.setAgentName(requests.getAgentName());

        long totalAmount = 0;
        List<OrderDetail> detailList = new ArrayList<>();
        for(OrderDetailRequestDTO orderDetailRequestDTO : requests.getDetail()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(orderDetailRequestDTO.getProductId());
            orderDetail.setProductName(orderDetailRequestDTO.getProductName());
            int quantity = orderDetailRequestDTO.getQuantity();
            orderDetail.setQuantity(quantity);
            long unitPrice = orderDetailRequestDTO.getProductSalePrice();
            orderDetail.setProductSalePrice(unitPrice);
            totalAmount += unitPrice*quantity;

            orderDetail.setOrder(order);

            detailList.add(orderDetail);
        }
        SourceType sourceType = requests.getSourceType();
        if (sourceType == SourceType.ONLINE)
        {
            order.setOrderStatus(OrderStatus.PENDING);
        }
        else if(sourceType == SourceType.DIRECT_DELIVERY)
        {
            order.setOrderStatus(OrderStatus.SHIPPING);
        }
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(totalAmount);
        order.setOrderDetailList(detailList);
        return new OrderResponseDTO(orderRepository.save(order));
    }

    public OrderResponseDTO updateOrderStatus (Long id, OrderStatus nextStatus, UserRole role){
        Order order = orderRepository.findById(id).orElseThrow();

        OrderStatus currentStatus = order.getOrderStatus();
        SourceType sourceType = order.getSourceType();

        boolean canChange = orderFlowService.canTransition(currentStatus,nextStatus,role,sourceType);
        if(!canChange){
            throw new RuntimeException("Permission denied: You cannot change status from "
                    + currentStatus + " to " + nextStatus + " as " + role + " via " + sourceType);
        }
        order.setOrderStatus(nextStatus);
        orderRepository.save(order);

        return  new OrderResponseDTO(order);
    }

}
