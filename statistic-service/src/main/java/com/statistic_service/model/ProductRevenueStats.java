package com.statistic_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_revenue_stats")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRevenueStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private long revenue;
}
