package com.import_order_service.Repository;

import com.import_order_service.Model.ImportOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportOrderDetailRepository extends JpaRepository<ImportOrderDetail,Long> {
}
