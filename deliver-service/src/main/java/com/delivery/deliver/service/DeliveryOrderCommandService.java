package com.delivery.deliver.service;

import com.delivery.deliver.dto.DeliveryOrderAssigneeDto;
import com.delivery.deliver.dto.DeliveryOrderCreateDto;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.dto.DeliveryOrderDto;

import java.util.List;

public interface DeliveryOrderCommandService {
    DeliveryOrderDto getOrder(Long id);

    DeliveryOrderDto createOrder(DeliveryOrderCreateDto createDto);

    DeliveryOrderDto changeDestination(Long id,DeliveryOrderDestinationDto destinationDto);

    DeliveryOrderDto assigneeOrderToCourier(DeliveryOrderAssigneeDto assigneeDto);

    List<DeliveryOrderDto> findAllOrdersByOwner();

    List<DeliveryOrderDto> findAllOrdersByAssignee();
}
