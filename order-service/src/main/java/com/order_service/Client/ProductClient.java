package com.order_service.Client;

import com.order_service.DTO.ProductReserveBatchRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "product-service", url = "http://product-service")
public interface ProductClient {

    @PutMapping("/product/checkQuantity")
    void reserveProductBatch(ProductReserveBatchRequest request);

}
