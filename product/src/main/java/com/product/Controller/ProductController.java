package com.product.Controller;

import com.product.DTO.ProductReserveBatchRequest;
import com.product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableDiscoveryClient
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/checkQuantity")
    public ResponseEntity<?> reserveProductBatch(@RequestBody ProductReserveBatchRequest request){
        try {
            productService.reserveProductBatch(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to reserve products: " + e.getMessage());
        }
    }

    @PostMapping("/product/handleImport")
    public ResponseEntity<?> handleImportOrder(@RequestBody ProductReserveBatchRequest request){
        try{
            productService.handleImportOrder(request);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
