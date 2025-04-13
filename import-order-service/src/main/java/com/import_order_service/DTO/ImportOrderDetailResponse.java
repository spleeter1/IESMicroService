package com.import_order_service.DTO;

import com.import_order_service.Model.ImportOrderDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportOrderDetailResponse {
    private long id;
    private long importOrderId;
    private int quantity;
    private long unitPrice;
    private long productId;
    private String productName;

    public ImportOrderDetailResponse(ImportOrderDetail detail){
        this.id = detail.getId();
        this.productId = detail.getProductId();
        this.productName = detail.getProductName();
        this.importOrderId = detail.getImportOrder().getId();
        this.quantity = detail.getQuantity();
        this.unitPrice = detail.getUnitPrice();
    }
}
