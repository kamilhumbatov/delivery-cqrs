package com.delivery.deliver.service;

import com.delivery.deliver.dto.DeliveryOrderDto;

public interface DeliveryOrderStatusService {

    DeliveryOrderDto changeOrderStatusToPickUp(long id);

    DeliveryOrderDto changeOrderStatusToDelivery(long id);

    DeliveryOrderDto changeOrderStatusToDelivered(long id);

    DeliveryOrderDto changeOrderStatusToCancel(long id);
}
