package com.order_service.DTO;

import com.order_service.model.OrderDetail;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDetailResponseDTO {
    private long productId;
    private String productName;
    private int quantity;
    private long productSalePrice;

    public OrderDetailResponseDTO (OrderDetail orderDetail){
        this.productId = orderDetail.getProductId();
        this.productName = orderDetail.getProductName();
        this.quantity = orderDetail.getQuantity();
        this.productSalePrice = orderDetail.getProductSalePrice();
    }
}
