package com.order_service.Service;

import com.order_service.DTO.OrderDetailRequestDTO;
import com.order_service.DTO.OrderRequestDTO;
import com.order_service.DTO.OrderResponseDTO;
import com.order_service.Enums.OrderStatus;
import com.order_service.Repository.OrderRepository;
import com.order_service.model.Order;
import com.order_service.model.OrderDetail;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

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
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setTotalAmount(totalAmount);
        order.setOrderDetailList(detailList);


        return new OrderResponseDTO(orderRepository.save(order));
    }

    public OrderResponseDTO handleBy

}
