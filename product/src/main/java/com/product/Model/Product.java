package com.product.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private long importPrice;

    @Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private long salePrice;

    @Column(nullable = false,columnDefinition = "BIGINT DEFAULT 0")
    private long quantity;

    @Column(nullable = false)
    private long supplierId;

    @Column(nullable = false)
    private String supplierName;
}
