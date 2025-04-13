package com.import_order_service.DTO;

import com.import_order_service.Model.ImportOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImportOrderDetailDTO {
    private long id;
    private long productId;
    private String productName;
    private int quantity;
    private long unitPrice;

    public ImportOrderDetailDTO(ImportOrderDetail importOrderDetail){
        this.id = importOrderDetail.getId();
        this.productId = importOrderDetail.getId();
        this.productName = importOrderDetail.getProductName();
        this.quantity = importOrderDetail.getQuantity();
        this.unitPrice = importOrderDetail.getUnitPrice();
    }

}
