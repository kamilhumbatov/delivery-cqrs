package com.delivery.service.impl;

import com.delivery.CurrentUserService;
import com.delivery.commands.ChangeOrderCoordinateCommand;
import com.delivery.commands.ChangeStatusDeliveryOrderCommand;
import com.delivery.dto.DeliveryOrderDestinationDto;
import com.delivery.enums.DeliveryOrderStatus;
import com.delivery.exception.DeliveryOrderStatusException;
import com.delivery.service.DeliveryOrderService;
import com.delivery.service.DeliveryOrderTrackService;
import com.delivery.user.domain.DeliveryOrder;
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
    public CompletableFuture<String> changeCoordinate(long id, DeliveryOrderDestinationDto destinationDto) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        //checkUserAccess(deliveryOrder);
        if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.DELIVERY) == 0) {
            return commandGateway.send(new ChangeOrderCoordinateCommand(deliveryOrder, destinationDto));
        }
        throw new DeliveryOrderStatusException(DeliveryOrderStatus.CANCELED, "Because order was delivered!");
    }
}
