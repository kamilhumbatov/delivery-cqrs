package com.delivery.service;

import com.delivery.dto.DeliveryOrderDto;

public interface DeliveryOrderStatusService {

    DeliveryOrderDto changeOrderStatusToPickUp(Long id);

    DeliveryOrderDto changeOrderStatusToDelivery(Long id);

    DeliveryOrderDto changeOrderStatusToDelivered(Long id);

    DeliveryOrderDto changeOrderStatusToCancel(Long id);
}
