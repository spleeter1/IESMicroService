package com.statistic_service.Service;

import com.statistic_service.Event.ImportOrderCreatedEvent;
import com.statistic_service.Event.OrderDetailCreatedEvent;
import com.statistic_service.Event.OrderEventCreated;
import com.statistic_service.Repository.AgentRevenueStatsRepository;
import com.statistic_service.Repository.ProductRevenueStatsRepository;
import com.statistic_service.Repository.SupplierImportStatsRepository;
import com.statistic_service.Repository.TimeRevenueStatsRepository;
import com.statistic_service.model.AgentRevenueStats;
import com.statistic_service.model.ProductRevenueStats;
import com.statistic_service.model.SupplierImportStats;
import com.statistic_service.model.TimeRevenueStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final SupplierImportStatsRepository supplierImportStatsRepository;
    private final TimeRevenueStatsRepository timeRevenueStatsRepository;
    private final ProductRevenueStatsRepository productRevenueStatsRepository;
    private final AgentRevenueStatsRepository agentRevenueStatsRepository;

    @Transactional
    public void updateSupplierImportStats(ImportOrderCreatedEvent request) {
        SupplierImportStats sis = supplierImportStatsRepository.findById(request.getSupplierId()).orElseGet(() -> {
            SupplierImportStats newStat = new SupplierImportStats();
            newStat.setSupplierId(request.getSupplierId());
            newStat.setSupplierName(request.getSupplierName());
            newStat.setQuantity(0);
            return newStat;
        });
        int quantity = sis.getQuantity();
        sis.setQuantity(quantity + request.getTotalQuantity());
        supplierImportStatsRepository.save(sis);
    }

    public List<SupplierImportStats> getAllSupplierImportStats() {
        return supplierImportStatsRepository.findAll();
    }

    @Transactional
    public void updateTimeRevenueStats(OrderEventCreated request) {
        int month = request.getOrderDate().getMonthValue();
        int quarter = (month-1)/3+1;
        int year = request.getOrderDate().getYear();
        TimeRevenueStats trs = timeRevenueStatsRepository.findByMonthAndYear(month,year).orElseGet(() ->{
            TimeRevenueStats newStats = new TimeRevenueStats();
            newStats.setMonth(month);
            newStats.setQuarter(quarter);
            newStats.setYear(year);
            newStats.setRevenue(0);
            return newStats;
        });
        trs.setRevenue(trs.getRevenue()+request.getTotalAmount());
        timeRevenueStatsRepository.save(trs);

    }
    public List<TimeRevenueStats> getAllTimeRevenueStats(){
        return timeRevenueStatsRepository.findAll();
    }

    @Transactional
    public void updateAgentRevenueStats(OrderEventCreated request) {
        AgentRevenueStats ars = agentRevenueStatsRepository.findById(request.getAgentId()).orElseGet(()->{
            AgentRevenueStats newStats = new AgentRevenueStats();
            newStats.setAgentId(request.getAgentId());
            newStats.setAgentName(request.getAgentName());
            newStats.setRevenue(0);
            return newStats;
        });
        ars.setRevenue(ars.getRevenue()+ request.getTotalAmount());
        agentRevenueStatsRepository.save(ars);
    }
    public List<AgentRevenueStats> getAllAgentRevenueStats(){
        return agentRevenueStatsRepository.findAll();
    }

    @Transactional
    public void updateProductRevenueStats(OrderEventCreated request) {
        List<OrderDetailCreatedEvent> productList = request.getOrderDetailCreatedEventList();
        for(OrderDetailCreatedEvent detail :productList){
            ProductRevenueStats prs = productRevenueStatsRepository.findById(detail.getProductId()).orElseGet(()->{
                ProductRevenueStats newStats = new ProductRevenueStats();
                newStats.setProductId(detail.getProductId());
                newStats.setProductName(detail.getProductName());
                newStats.setRevenue(0);
                return newStats;
            });
            prs.setRevenue(prs.getRevenue() + detail.getAmount());
            productRevenueStatsRepository.save(prs);
        }
    }

    public List<ProductRevenueStats> getAllProductRevenueStats(){
        return productRevenueStatsRepository.findAll();
    }

    @Transactional
    public void handleFullOrderStats(OrderEventCreated e) {
        updateAgentRevenueStats(e);
        updateProductRevenueStats(e);
        updateTimeRevenueStats(e);
    }
}
