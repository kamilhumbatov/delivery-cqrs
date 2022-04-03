package com.delivery.deliver.service.handlers;

import com.delivery.deliver.aggregates.OrderAggregate;
import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.dto.DeliveryOrderDto;

import java.util.List;

public interface DeliveryOrderQueryHandlerService {
    DeliveryOrderDto getOrder(String id);

    DeliveryOrderDto changeDestination(String id, DeliveryOrderDestinationDto destinationDto);

    List<DeliveryOrderDto> findAllOrdersByOwner();

    List<DeliveryOrderDto> findAllOrdersByAssignee();
}
