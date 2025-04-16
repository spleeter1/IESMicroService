package com.statistic_service.Repository;

import com.statistic_service.model.SupplierImportStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierImportStatsRepository extends JpaRepository<SupplierImportStats,Long> {
}
