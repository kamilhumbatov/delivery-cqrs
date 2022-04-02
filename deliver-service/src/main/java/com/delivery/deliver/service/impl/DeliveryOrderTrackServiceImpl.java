package com.delivery.deliver.service.impl;

import com.delivery.CurrentUserService;
import com.delivery.deliver.commands.ChangeOrderCoordinateCommand;
import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.exception.DeliveryOrderStatusException;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.DeliveryOrderTrackService;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class DeliveryOrderTrackServiceImpl implements DeliveryOrderTrackService {

    private final DeliveryOrderService orderService;
    private final CurrentUserService currentUserService;
    private final CommandGateway commandGateway;

    @Override
    public CompletableFuture<String> changeCoordinate(String id, DeliveryOrderDestinationDto destinationDto) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        //checkUserAccess(deliveryOrder);
        if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.DELIVERY) == 0) {
            ChangeOrderCoordinateCommand command =
                    ChangeOrderCoordinateCommand.builder()
                            .orderId(deliveryOrder.getId())
                            .longitude(destinationDto.getLongitude())
                            .latitude(destinationDto.getLatitude())
                            .build();
            return commandGateway.send(command);
        }
        throw new DeliveryOrderStatusException(deliveryOrder.getStatus(), "Because order was not delivery!");
    }
}
