package com.delivery.deliver.service.commands;


import com.delivery.deliver.dto.DeliveryOrderCreateDto;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;

public interface OrderCommandService {
    String createOrder(DeliveryOrderCreateDto createDto);

    String changeCoordinate(String id, DeliveryOrderDestinationDto destinationDto);
}
