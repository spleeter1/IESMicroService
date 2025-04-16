package com.statistic_service.Service;

import com.statistic_service.Event.ImportOrderCreatedEvent;
import com.statistic_service.Repository.SupplierImportStatsRepository;
import com.statistic_service.model.SupplierImportStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final SupplierImportStatsRepository supplierImportStatsRepository;
    @Transactional
    public void updateSupplierImportStats(ImportOrderCreatedEvent request){
        SupplierImportStats sis = supplierImportStatsRepository.findById(request.getSupplierId()).orElseThrow();
        int quantity = sis.getQuantity();
        sis.setQuantity(quantity + request.getTotalQuantity());
    }

    public List<SupplierImportStats> getAllSupplierImportStats(){
        return supplierImportStatsRepository.findAll();
    }
}
