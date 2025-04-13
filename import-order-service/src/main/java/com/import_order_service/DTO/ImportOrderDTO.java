package com.import_order_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportOrderDTO {
    private long id;
    private LocalDate orderDate;
    private int totalQuantity;
    private int totalAmount;
    private long supplierId;
    private String supplierName;
    private List<ImportOrderDetailDTO> detailDTOList;

}
