package com.delivery.deliver.service;

import com.delivery.deliver.domain.DeliveryOrder;

import java.util.List;

public interface DeliveryOrderService {
    DeliveryOrder findById(String id);

    DeliveryOrder findByIdAndOwner(String id, String owner);

    DeliveryOrder findByIdAndAssignee(String id, String assignee);

    DeliveryOrder save(DeliveryOrder deliveryOrder);

    List<DeliveryOrder> findAllOrdersByOwner(String username);

    List<DeliveryOrder> findAllOrdersByAssignee(String username);
}
