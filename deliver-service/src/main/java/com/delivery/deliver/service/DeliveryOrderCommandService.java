package com.delivery.deliver.service;

import com.delivery.deliver.dto.DeliveryOrderAssigneeDto;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.dto.DeliveryOrderDto;

import java.util.List;

public interface DeliveryOrderCommandService {
    DeliveryOrderDto getOrder(String id);

    DeliveryOrderDto changeDestination(String id, DeliveryOrderDestinationDto destinationDto);

    List<DeliveryOrderDto> findAllOrdersByOwner();

    List<DeliveryOrderDto> findAllOrdersByAssignee();

    List<Object> listEventsForAccount(String orderId);
}
