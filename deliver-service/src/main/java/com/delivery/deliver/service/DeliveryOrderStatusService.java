package com.delivery.deliver.service;

import com.delivery.deliver.dto.DeliveryOrderDto;

public interface DeliveryOrderStatusService {

    DeliveryOrderDto changeOrderStatusToPickUp(String id);

    DeliveryOrderDto changeOrderStatusToDelivery(String id);

    DeliveryOrderDto changeOrderStatusToDelivered(String id);

    DeliveryOrderDto changeOrderStatusToCancel(String id);
}
