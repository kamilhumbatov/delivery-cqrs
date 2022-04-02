package com.delivery.deliver.service.impl;

import com.delivery.CurrentUserService;
import com.delivery.deliver.commands.CreateOrderCommand;
import com.delivery.deliver.dto.DeliveryOrderCreateDto;
import com.delivery.deliver.service.DeliveryOrderCreateService;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.mapper.DeliveryOrderCreateMapper;
import com.delivery.deliver.service.mapper.DeliveryOrderDestinationCreateMapper;
import com.delivery.deliver.service.mapper.DeliveryOrderMapper;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class DeliveryOrderCreateServiceImpl implements DeliveryOrderCreateService {

    private final DeliveryOrderMapper mapper;
    private final DeliveryOrderService orderService;
    private final CurrentUserService currentUserService;
    private final DeliveryOrderCreateMapper createMapper;
    private final DeliveryOrderDestinationCreateMapper destinationCreateMapper;
    private final CommandGateway commandGateway;

    @Override
    public CompletableFuture<String> createOrder(DeliveryOrderCreateDto createDto) {
        CreateOrderCommand command = new CreateOrderCommand(
                UUID.randomUUID().toString(),
                "currentUser",
                createDto.getLatitude(),
                createDto.getLongitude()
        );
        return commandGateway.send(command);
    }
}
