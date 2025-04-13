package com.order_service.DTO;

import com.order_service.Enums.SourceType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {
    private long agentId;
    private String agentName;
    private List<OrderDetailRequestDTO> detail;
    private SourceType sourceType;
}
