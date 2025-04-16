package com.statistic_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRevenueStatsRepository extends JpaRepository<TimeRevenueStatsRepository,Long> {
}
