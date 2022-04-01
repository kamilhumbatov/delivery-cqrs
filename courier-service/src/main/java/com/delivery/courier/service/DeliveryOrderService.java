package com.delivery.courier.service;

import com.delivery.courier.dto.DeliveryOrderDto;

public interface DeliveryOrderService {

    DeliveryOrderDto changeOrderStatusToPickUp(Long id);

    DeliveryOrderDto changeOrderStatusToDelivery(Long id);

    DeliveryOrderDto changeOrderStatusToDelivered(Long id);
}
