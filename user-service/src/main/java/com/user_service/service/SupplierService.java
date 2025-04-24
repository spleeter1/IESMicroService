package com.user_service.service;

import com.user_service.model.Supplier;
import com.user_service.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public List<Supplier> getAllSupplier(){
        return supplierRepository.findAll();
    }
}
