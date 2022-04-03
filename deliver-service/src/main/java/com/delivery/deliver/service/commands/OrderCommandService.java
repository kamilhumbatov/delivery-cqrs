package com.delivery.deliver.service.commands;


import com.delivery.deliver.dto.DeliveryOrderCreateDto;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.enums.DeliveryOrderStatus;

public interface OrderCommandService {
    String createOrder(DeliveryOrderCreateDto createDto);

    String changeCoordinate(String id, DeliveryOrderDestinationDto destinationDto);

    String changeStatus(String id, DeliveryOrderStatus status);

    String assigneeOrder(String id, String assignee);

    String changeDestination(String id, DeliveryOrderDestinationDto destinationDto);
}
