package com.import_order_service.Service;

import com.import_order_service.DTO.*;
import com.import_order_service.Event.ImportOrderCreatedEvent;
import com.import_order_service.FeignClient.ProductClient;
import com.import_order_service.Model.ImportOrder;
import com.import_order_service.Model.ImportOrderDetail;
import com.import_order_service.Producer.ImportOrderEventProducer;
import com.import_order_service.Repository.ImportOrderRepository;
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
    private final ImportOrderEventProducer importOrderEventProducer;
    private final ProductClient productClient;
    @Transactional
    public ImportOrderResponse createOrder (ImportOrderRequest requests){
        ImportOrder importOrder = new ImportOrder();
        importOrder.setSupplierId(requests.getSupplierId());
        importOrder.setOrderDate(LocalDate.now());
        importOrder.setSupplierName(requests.getSupplierName());

        int totalQuantity = 0;
        long totalAmount = 0;
        List<ImportOrderDetail> detailList = new ArrayList<>();
        ProductReserveBatchRequest productReserveBatchRequest = new ProductReserveBatchRequest();
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

            detailList.add(detail);

            ProductReserveRequest productReserveRequest = new ProductReserveRequest(detail.getId(),detail.getQuantity());
            productReserveBatchRequest.getItems().add(productReserveRequest);
        }

        importOrder.setTotalQuantity(totalQuantity);
        importOrder.setTotalAmount(totalAmount);
        importOrder.setOrderDetailList(detailList);
        ImportOrderResponse importOrderResponse = new ImportOrderResponse(importOrderRepository.save(importOrder));

        // gửi sang product service để tăng số lượng
        try {
            productClient.increaseStock(productReserveBatchRequest);
            System.out.println("Tăng số lượng sản phẩm thành công!");
        } catch (Exception e) {
            System.err.println("Gọi API thất bại: " + e.getMessage());
        }

        // gửi số lượng đến supplierimport
        ImportOrderCreatedEvent e = new ImportOrderCreatedEvent(importOrder.getSupplierId(),importOrder.getSupplierName(),importOrder.getTotalQuantity());
        importOrderEventProducer.send(e);

        return importOrderResponse;
    }
}
