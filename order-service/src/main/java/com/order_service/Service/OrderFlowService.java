package com.order_service.Service;

import com.order_service.Enums.OrderStatus;
import com.order_service.Enums.SourceType;
import com.order_service.Enums.UserRole;

import static com.order_service.Enums.OrderStatus.*;

public class OrderFlowService {
    public boolean canTransition(OrderStatus current, OrderStatus next, UserRole role, SourceType sourceType) {
        switch (sourceType) {
            case ONLINE -> {
                return switch (role) {
                    case WAREHOUSE -> (current == PENDING && next == CANCELED) ||
                            (current == PENDING && next == SHIPPING);
                    case AGENT -> (current == SHIPPING && next == DELIVERED);
                    case ADMIN -> true;
                    default -> false;
                };
            }

            case DIRECT_DELIVERY -> {
                return switch (role) {
                    case WAREHOUSE -> (current == SHIPPING && next == DELIVERED) ;
                    case ADMIN -> true;
                    default -> false;
                };
            }
        }
        return false;
    }
}
