package com.delivery.deliver.service;

public interface DeliveryOrderStatusService {

    String changeOrderStatusToPickUp(String id);

    String changeOrderStatusToDelivery(String id);

    String changeOrderStatusToDelivered(String id);

    String changeOrderStatusToCancel(String id);
}
