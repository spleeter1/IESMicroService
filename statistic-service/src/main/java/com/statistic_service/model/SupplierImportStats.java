package com.statistic_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "supplier_import_stats")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierImportStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long supplierId;

    @Column(nullable = false)
    private String supplierName;

    @Column(nullable = false)
    private int quantity;
}