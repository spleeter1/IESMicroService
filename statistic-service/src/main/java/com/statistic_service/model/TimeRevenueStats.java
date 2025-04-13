package com.statistic_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "time_revenue_stats")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TimeRevenueStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int month;

    @Column(nullable = false)
    private int quarter;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private long revenue;

}
