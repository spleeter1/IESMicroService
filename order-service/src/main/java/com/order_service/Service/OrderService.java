package com.order_service.Service;

import com.order_service.Client.ProductClient;
import com.order_service.DTO.*;
import com.order_service.Enums.OrderStatus;
import com.order_service.Enums.SourceType;
import com.order_service.Enums.UserRole;
import com.order_service.Event.OrderCreatedEvent;
import com.order_service.Event.OrderDetailCreatedEvent;
import com.order_service.Producer.OrderCompletedEventProducer;
import com.order_service.Repository.OrderRepository;
import com.order_service.model.Order;
import com.order_service.model.OrderDetail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderFlowService orderFlowService;
    private final ProductClient productClient;
    private final OrderCompletedEventProducer producer;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO requests) {
        Order order = new Order();
        order.setAgentId(requests.getAgentId());
        order.setAgentName(requests.getAgentName());

        long totalAmount = 0;
        List<OrderDetail> detailList = new ArrayList<>();
        ProductReserveBatchRequest productReserveBatchRequest = new ProductReserveBatchRequest();
        for (OrderDetailRequestDTO orderDetailRequestDTO : requests.getDetail()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(orderDetailRequestDTO.getProductId());
            orderDetail.setProductName(orderDetailRequestDTO.getProductName());
            int quantity = orderDetailRequestDTO.getQuantity();
            orderDetail.setQuantity(quantity);
            long unitPrice = orderDetailRequestDTO.getProductSalePrice();
            orderDetail.setProductSalePrice(unitPrice);
            totalAmount += unitPrice * quantity;

            orderDetail.setOrder(order);

            detailList.add(orderDetail);

            ProductReserveRequest productReserveRequest = new ProductReserveRequest(orderDetail.getProductId(), orderDetail.getQuantity());
            productReserveBatchRequest.getItems().add(productReserveRequest);
        }

        SourceType sourceType = requests.getSourceType();
        if (sourceType == SourceType.ONLINE) {
            order.setOrderStatus(OrderStatus.PENDING);
        } else if (sourceType == SourceType.DIRECT_DELIVERY) {
            order.setOrderStatus(OrderStatus.SHIPPING);
        }
        //gọi api -> product service. Kiểm tra đủ slg mới cho đặt
        try {
            productClient.reserveProductBatch(productReserveBatchRequest);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(totalAmount);
        order.setOrderDetailList(detailList);


        return new OrderResponseDTO(orderRepository.save(order));

    }

    @Transactional
    public OrderResponseDTO updateOrderStatus(Long id, OrderStatus nextStatus, UserRole role) {
        Order order = orderRepository.findById(id).orElseThrow();

        OrderStatus currentStatus = order.getOrderStatus();
        SourceType sourceType = order.getSourceType();

        boolean canChange = orderFlowService.canTransition(currentStatus, nextStatus, role, sourceType);
        if (!canChange) {
            throw new RuntimeException("Permission denied: You cannot change status from "
                    + currentStatus + " to " + nextStatus + " as " + role + " via " + sourceType);
        }
        order.setOrderStatus(nextStatus);
        orderRepository.save(order);
        // đặt thành công thì kafka request -> statistic service

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        orderCreatedEvent.setAgentId(order.getAgentId());
        orderCreatedEvent.setAgentName(order.getAgentName());
        orderCreatedEvent.setTotalAmount(order.getTotalAmount());
        orderCreatedEvent.setOrderDate(order.getOrderDate());

        List<OrderDetailCreatedEvent> detailList = new ArrayList<>();
        for (OrderDetail orderDetail : order.getOrderDetailList()) {
            OrderDetailCreatedEvent detail = new OrderDetailCreatedEvent();
            detail.setProductId(orderDetail.getProductId());;
            detail.setProductName(orderDetail.getProductName());
            detail.setAmount(orderDetail.getProductSalePrice() * orderDetail.getQuantity());
            detailList.add(detail);
        }
        orderCreatedEvent.setOrderDetailCreatedEventList(detailList);
        producer.send(orderCreatedEvent);

        return new OrderResponseDTO(order);
    }

}
