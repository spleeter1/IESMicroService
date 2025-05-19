package com.import_order_service.Service;

import com.import_order_service.Event.ImportOrderCreatedEvent;
import com.import_order_service.FeignClient.ProductClient;
import com.import_order_service.Model.ImportOrder;
import com.import_order_service.Model.ImportOrderDetail;
//import com.import_order_service.DTO.ProductReserveBatchRequest;
//import com.import_order_service.DTO.ProductReserveRequest;
import com.import_order_service.Producer.ImportOrderEventProducer;
import com.import_order_service.Repository.ImportOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ImportOrderService {
    private final ImportOrderRepository importOrderRepository;
    private final ImportOrderEventProducer importOrderEventProducer;
    private final ProductClient productClient;

    @Transactional
    public ImportOrder createOrder(ImportOrder importOrder) {
        importOrder.setOrderDate(LocalDate.now());

        int totalQuantity = 0;
        long totalAmount = 0;

//        ProductReserveBatchRequest reserveBatch = new ProductReserveBatchRequest();

        for (ImportOrderDetail detail : importOrder.getOrderDetailList()) {
            detail.setImportOrder(importOrder);
            int quantity = detail.getQuantity();
            long unitPrice = detail.getUnitPrice();

            totalQuantity += quantity;
            totalAmount += unitPrice * quantity;

//            reserveBatch.getItems().add(
//                    new ProductReserveRequest(detail.getProductId(), quantity)
//            );
        }

        importOrder.setTotalQuantity(totalQuantity);
        importOrder.setTotalAmount(totalAmount);

        // Gọi external API trước để đảm bảo tồn kho tăng thành công
//        try {
//            productClient.increaseStock(reserveBatch);
//            System.out.println("✅ Tăng số lượng sản phẩm thành công!");
//        } catch (Exception e) {
//            System.err.println("❌ Gọi ProductService thất bại: " + e.getMessage());
//            throw new RuntimeException("Tăng tồn kho thất bại, đơn hàng sẽ không được lưu.");
//        }

        // Nếu không có lỗi, tiến hành lưu vào DB
        ImportOrder saved = importOrderRepository.save(importOrder);

        // Gửi event sang supplier-statistic
        try {
            ImportOrderCreatedEvent event = new ImportOrderCreatedEvent(
                    saved.getSupplierId(),
                    saved.getSupplierName(),
                    saved.getTotalQuantity()
            );
            importOrderEventProducer.send(event);
        } catch (Exception e) {
            System.err.println("⚠️ Gửi event Kafka thất bại: " + e.getMessage());
        }

        return saved;
    }
}
