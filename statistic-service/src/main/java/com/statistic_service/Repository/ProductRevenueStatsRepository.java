package com.statistic_service.Repository;

import com.statistic_service.model.ProductRevenueStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRevenueStatsRepository extends JpaRepository<ProductRevenueStats,Long> {
}
