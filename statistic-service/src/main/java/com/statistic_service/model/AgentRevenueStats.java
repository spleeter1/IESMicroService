package com.statistic_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "agent_revenue_stats")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AgentRevenueStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long agentId;

    @Column(nullable = false)
    private String agentName;

    @Column(nullable = false)
    private long revenue;
}
