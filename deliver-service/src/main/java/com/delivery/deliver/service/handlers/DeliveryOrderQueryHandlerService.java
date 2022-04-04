package com.delivery.deliver.service.handlers;

import com.delivery.deliver.dto.DeliveryOrderDto;

import java.util.List;

public interface DeliveryOrderQueryHandlerService {
    DeliveryOrderDto getOrder(String id, String role);

    List<DeliveryOrderDto> findAllOrdersByOwner();

    List<DeliveryOrderDto> findAllOrdersByAssignee();
}
