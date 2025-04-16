package com.statistic_service.Repository;

import com.statistic_service.model.AgentRevenueStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRevenueStatsRepository extends JpaRepository<AgentRevenueStats,Long> {
}
