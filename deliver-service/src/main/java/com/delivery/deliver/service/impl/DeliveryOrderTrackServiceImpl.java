package com.delivery.deliver.service.impl;

import com.delivery.CurrentUserService;
import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.DeliveryOrderTrackService;
import com.delivery.deliver.service.commands.OrderCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderTrackServiceImpl implements DeliveryOrderTrackService {

    private final DeliveryOrderService orderService;
    private final CurrentUserService currentUserService;
    private final OrderCommandService orderCommandService;

    @Override
    public String changeCoordinate(String id, DeliveryOrderDestinationDto destinationDto) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        //checkUserAccess(deliveryOrder);
        //if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.DELIVERY) == 0)
        {
            //return commandGateway.sendAndWait(command);
            return orderCommandService.changeCoordinate(id, destinationDto);
        }
        //throw new DeliveryOrderStatusException(deliveryOrder.getStatus(), "Because order was not delivery!");
    }
}
