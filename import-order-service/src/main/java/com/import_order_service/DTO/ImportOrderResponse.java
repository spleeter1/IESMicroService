package com.import_order_service.DTO;

import com.import_order_service.Model.ImportOrder;
import com.import_order_service.Model.ImportOrderDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class ImportOrderResponse {
    private long id;
    private LocalDate orderDate;
    private long supplierId;
    private String supplierName;
    private int totalQuantity;
    private long totalAmount;
    private List<ImportOrderDetailResponse> detailList;
    public ImportOrderResponse (ImportOrder importOrder){
        this.id = importOrder.getId();
        this.supplierId = importOrder.getSupplierId();
        this.supplierName= importOrder.getSupplierName();
        this.orderDate = importOrder.getOrderDate();
        this.totalQuantity = importOrder.getTotalQuantity();
        this.totalAmount = importOrder.getTotalAmount();
        this.detailList = new ArrayList<>();
        for (ImportOrderDetail detail : importOrder.getOrderDetailList()){
            this.detailList.add(new ImportOrderDetailResponse(detail));
        }
    }
}
