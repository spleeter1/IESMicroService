package com.import_order_service.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "import_order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImportOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private int totalQuantity;

    @Column(nullable = false)
    private long totalAmount;

    @Column(nullable = false)
    private long supplierId;

    @Column(nullable = false)
    private String supplierName;

    @OneToMany(mappedBy = "importOrder",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ImportOrderDetail> orderDetailList;

}
