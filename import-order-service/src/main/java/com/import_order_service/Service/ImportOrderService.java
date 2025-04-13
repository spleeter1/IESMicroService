package com.import_order_service.Service;

import com.import_order_service.DTO.ImportOrderDetailRequest;
import com.import_order_service.DTO.ImportOrderRequest;
import com.import_order_service.DTO.ImportOrderResponse;
import com.import_order_service.Model.ImportOrder;
import com.import_order_service.Model.ImportOrderDetail;
import com.import_order_service.Repository.ImportOrderDetailRepository;
import com.import_order_service.Repository.ImportOrderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportOrderService {
    private final ImportOrderRepository importOrderRepository;

    @Transactional
    public ImportOrderResponse createOrder (ImportOrderRequest requests){
        ImportOrder importOrder = new ImportOrder();
        importOrder.setSupplierId(requests.getSupplierId());
        importOrder.setOrderDate(LocalDate.now());
        importOrder.setSupplierName(requests.getSupplierName());

        int totalQuantity = 0;
        long totalAmount = 0;
        List<ImportOrderDetail> detailList = new ArrayList<>();
        for(ImportOrderDetailRequest detailRequest : requests.getOrderDetails()){
            ImportOrderDetail detail = new ImportOrderDetail();
            detail.setImportOrder(importOrder);
            detail.setProductId(detailRequest.getProductId());
            detail.setProductName(detailRequest.getProductName());
            int quantity = detailRequest.getQuantity();
            detail.setQuantity(quantity);
            long unitPrice = detailRequest.getUnitPrice();
            detail.setUnitPrice(unitPrice);

            totalAmount+= unitPrice*quantity;
            totalQuantity+=quantity;
        }
        importOrder.setTotalQuantity(totalQuantity);
        importOrder.setTotalAmount(totalAmount);

        return new ImportOrderResponse(importOrderRepository.save(importOrder));
    }
}
