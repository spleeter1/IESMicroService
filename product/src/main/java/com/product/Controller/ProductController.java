package com.product.Controller;

import com.product.DTO.ProductReserveBatchRequest;
import com.product.Model.Product;
import com.product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/checkQuantity")
    public ResponseEntity<?> reserveProductBatch(@RequestBody ProductReserveBatchRequest request){
        try {
            productService.reserveProductBatch(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to reserve products: " + e.getMessage());
        }
    }

    @PostMapping("/handle-import")
    public ResponseEntity<?> handleImportOrder(@RequestBody ProductReserveBatchRequest request){
        try{
            productService.handleImportOrder(request);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<?> getAllProductBySupplierId(@PathVariable long supplierId){
        try {
            return ResponseEntity.ok(productService.getAllProductBySupplierId(supplierId));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addNewProduct(@RequestBody Product request){
        try{
            Product product = productService.addNewProduct(request);
            return ResponseEntity.ok().body(product);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("error");
        }
    }
}