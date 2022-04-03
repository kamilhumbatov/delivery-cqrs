package com.delivery.deliver.service.commands;

import com.delivery.deliver.commands.ChangeCoordinateCommand;
import com.delivery.deliver.commands.ChangeStatusCommand;
import com.delivery.deliver.commands.CreateOrderCommand;
import com.delivery.deliver.dto.DeliveryOrderCreateDto;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService {

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
        return commandGateway.sendAndWait(command);
    }

    @Override
    public String changeCoordinate(String id, DeliveryOrderDestinationDto destinationDto) {
        ChangeCoordinateCommand command = ChangeCoordinateCommand.builder()
                .id(id)
                .longitude(destinationDto.getLongitude())
                .latitude(destinationDto.getLatitude())
                .build();
        return commandGateway.sendAndWait(command);
    }

    @Override
    public String changeStatus(String id, DeliveryOrderStatus status) {
        ChangeStatusCommand command = ChangeStatusCommand.builder()
                .id(id)
                .status(status)
                .build();
        return commandGateway.sendAndWait(command);
    }
}
