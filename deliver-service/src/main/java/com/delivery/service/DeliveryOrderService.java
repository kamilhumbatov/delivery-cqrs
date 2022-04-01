package com.delivery.service;

import com.delivery.user.domain.DeliveryOrder;

import java.util.List;

public interface DeliveryOrderService {
    DeliveryOrder findById(long id);

    DeliveryOrder save(DeliveryOrder deliveryOrder);

    List<DeliveryOrder> findAllOrdersByOwner(String username);

    List<DeliveryOrder> findAllOrdersByAssignee(String username);
}
