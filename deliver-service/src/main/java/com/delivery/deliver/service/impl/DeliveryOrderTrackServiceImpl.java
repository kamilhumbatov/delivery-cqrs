package com.delivery.deliver.service.impl;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.exception.DeliveryOrderStatusException;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.DeliveryOrderTrackService;
import com.delivery.deliver.service.commands.OrderCommandService;
import com.delivery.deliver.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderTrackServiceImpl implements DeliveryOrderTrackService {

    private final SecurityUtil securityUtil;
    private final DeliveryOrderService orderService;
    private final OrderCommandService orderCommandService;

    @Override
    public String changeCoordinate(String id, DeliveryOrderDestinationDto destinationDto) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        securityUtil.checkUserAccess(deliveryOrder);
        if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.DELIVERY) == 0) {
            return orderCommandService.changeCoordinate(id, destinationDto);
        }
        throw new DeliveryOrderStatusException(deliveryOrder.getStatus(), "Because order was not delivery!");
    }
}
