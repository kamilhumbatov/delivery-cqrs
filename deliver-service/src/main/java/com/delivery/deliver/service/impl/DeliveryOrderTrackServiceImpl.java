package com.delivery.deliver.service.impl;

import com.delivery.CurrentUserService;
import com.delivery.deliver.commands.ChangeCoordinateCommand;
import com.delivery.deliver.commands.CreditMoneyCommand;
import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.DeliveryOrderTrackService;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderTrackServiceImpl implements DeliveryOrderTrackService {

    private final DeliveryOrderService orderService;
    private final CurrentUserService currentUserService;
    private final CommandGateway commandGateway;

    @Override
    public String changeCoordinate(String id, DeliveryOrderDestinationDto destinationDto) {
        //DeliveryOrder deliveryOrder = orderService.findById(id);
        //checkUserAccess(deliveryOrder);
        //if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.DELIVERY) == 0)
        {
            CreditMoneyCommand command2 = CreditMoneyCommand.builder()
                    .id(id)
                    .longitude(destinationDto.getLongitude())
                    .latitude(destinationDto.getLatitude())
                    .build();
            commandGateway.sendAndWait(command2);
            //return commandGateway.sendAndWait(command);
            return "OK";
        }
        //throw new DeliveryOrderStatusException(deliveryOrder.getStatus(), "Because order was not delivery!");
    }
}
