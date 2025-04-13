package com.order_service.DTO;

import com.order_service.model.OrderDetail;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDetailResponseDTO {
    private long id;
    private String productName;
    private int quantity;
    private long productSalePrice;

    public OrderDetailResponseDTO (OrderDetail orderDetail){

    }
}
