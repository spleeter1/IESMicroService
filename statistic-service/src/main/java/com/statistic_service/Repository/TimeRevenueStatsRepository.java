package com.statistic_service.Repository;

import com.statistic_service.model.TimeRevenueStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimeRevenueStatsRepository extends JpaRepository<TimeRevenueStats,Long> {
    Optional<TimeRevenueStats> findByMonthAndYear(int month,int year);
}
