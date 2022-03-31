package com.delivery.service;

import com.delivery.dto.ParcelDeliveryOrderAssigneeDto;
import com.delivery.dto.ParcelDeliveryOrderDto;

import java.util.List;

public interface ParcelDeliveryOrderService {
    ParcelDeliveryOrderDto getOrder(Long id);

    ParcelDeliveryOrderDto createOrder(ParcelDeliveryOrderDto createDto);

    ParcelDeliveryOrderDto cancelOrder(Long id);

    ParcelDeliveryOrderDto changeOrderStatusToPickUp(Long id);

    ParcelDeliveryOrderDto changeOrderStatusToDelivery(Long id);

    ParcelDeliveryOrderDto changeOrderStatusToDelivered(Long id);

    ParcelDeliveryOrderDto assigneeOrderToCourier(ParcelDeliveryOrderAssigneeDto assigneeDto);

    List<ParcelDeliveryOrderDto> findAllOrdersByOwner(String owner);

    List<ParcelDeliveryOrderDto> findAllOrdersByAssignee(String assignee);
}
