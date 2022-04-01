package com.delivery.service;

import com.delivery.dto.DeliveryOrderAssigneeDto;
import com.delivery.dto.DeliveryOrderCreateDto;
import com.delivery.dto.DeliveryOrderDestinationDto;
import com.delivery.dto.DeliveryOrderDto;

import java.util.List;

public interface DeliveryOrderService {
    DeliveryOrderDto getOrder(Long id);

    DeliveryOrderDto createOrder(DeliveryOrderCreateDto createDto);

    DeliveryOrderDto changeDestination(Long id,DeliveryOrderDestinationDto destinationDto);

    DeliveryOrderDto assigneeOrderToCourier(DeliveryOrderAssigneeDto assigneeDto);

    List<DeliveryOrderDto> findAllOrdersByOwner();

    List<DeliveryOrderDto> findAllOrdersByAssignee();
}
