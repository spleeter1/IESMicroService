package com.user_service.controller;

import com.user_service.model.Supplier;
import com.user_service.service.SupplierService;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping("/")
    public ResponseEntity<?> getAllSupplier(){
        try {
            List<Supplier> supplierList = supplierService.getAllSupplier();
            return ResponseEntity.ok().body(supplierList);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("error");
        }
    }
}
