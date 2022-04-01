package com.delivery.service;

import com.delivery.dto.DeliveryOrderDto;

public interface DeliveryOrderStatusCommandService {

    DeliveryOrderDto changeOrderStatusToPickUp(long id);

    DeliveryOrderDto changeOrderStatusToDelivery(long id);

    DeliveryOrderDto changeOrderStatusToDelivered(long id);

    DeliveryOrderDto changeOrderStatusToCancel(long id);
}
