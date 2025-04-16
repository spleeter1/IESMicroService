package com.import_order_service.FeignClient;

import com.import_order_service.DTO.ProductReserveBatchRequest;
import com.import_order_service.DTO.ProductReserveRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", url = "http://product-service")
public interface ProductClient {

    @PutMapping("/product/handleImport")
    void increaseStock(@RequestBody ProductReserveBatchRequest productReserveBatchRequest);
}
