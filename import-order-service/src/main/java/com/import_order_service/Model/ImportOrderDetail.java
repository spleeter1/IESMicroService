package com.import_order_service.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "import_orders_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImportOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "import_order_id",nullable = false)
    private ImportOrder importOrder;

    @Column(nullable = false)
    private long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private long unitPrice;
}
