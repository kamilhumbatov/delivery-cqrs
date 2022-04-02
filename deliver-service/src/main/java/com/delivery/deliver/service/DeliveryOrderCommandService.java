package com.delivery.deliver.service;

import com.delivery.deliver.dto.DeliveryOrderAssigneeDto;
import com.delivery.deliver.dto.DeliveryOrderCreateDto;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.dto.DeliveryOrderDto;

import java.util.List;

public interface DeliveryOrderCommandService {
    DeliveryOrderDto getOrder(String id);

    DeliveryOrderDto createOrder(DeliveryOrderCreateDto createDto);

    DeliveryOrderDto changeDestination(String id,DeliveryOrderDestinationDto destinationDto);

    DeliveryOrderDto assigneeOrderToCourier(DeliveryOrderAssigneeDto assigneeDto);

    List<DeliveryOrderDto> findAllOrdersByOwner();

    List<DeliveryOrderDto> findAllOrdersByAssignee();
}
