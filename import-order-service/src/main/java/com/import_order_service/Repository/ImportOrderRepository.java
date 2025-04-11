package com.import_order_service.Repository;

import com.import_order_service.Model.ImportOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportOrderRepository extends JpaRepository<ImportOrder,Long> {
}
