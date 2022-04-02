package com.delivery.deliver.service.impl;

import com.delivery.deliver.commands.CreateOrderCommand;
import com.delivery.deliver.dto.DeliveryOrderCreateDto;
import com.delivery.deliver.service.DeliveryOrderCreateService;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryOrderCreateServiceImpl implements DeliveryOrderCreateService {

    private final CommandGateway commandGateway;

    @Override
    public String createOrder(DeliveryOrderCreateDto createDto) {
        CreateOrderCommand command =
                CreateOrderCommand.builder()
                        .orderId(UUID.randomUUID().toString())
                        .owner("currentUser")
                        .latitude(createDto.getLatitude())
                        .longitude(createDto.getLongitude())
                        .build();
        String result = commandGateway.sendAndWait(command);
        return result;
    }
}
