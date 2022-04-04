package com.delivery.deliver.service.impl;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.exception.DeliveryOrderStatusException;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.DeliveryOrderStatusService;
import com.delivery.deliver.service.commands.OrderCommandService;
import com.delivery.deliver.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderStatusServiceImpl implements DeliveryOrderStatusService {

    private final SecurityUtil securityUtil;
    private final DeliveryOrderService orderService;
    private final OrderCommandService orderCommand;

    @Override
    public String changeOrderStatusToCancel(String id) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        securityUtil.checkUserOwner(deliveryOrder);
        if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.DELIVERED) == 0) {
            throw new DeliveryOrderStatusException(DeliveryOrderStatus.CANCELED, "Because order was delivered!");
        }
        return changeOrderStatus(deliveryOrder, DeliveryOrderStatus.CANCELED);
    }

    @Override
    public String changeOrderStatusToPickUp(String id) {
        return changeOrderStatus(id, DeliveryOrderStatus.PICKUP);
    }

    @Override
    public String changeOrderStatusToDelivery(String id) {
        return changeOrderStatus(id, DeliveryOrderStatus.DELIVERY);
    }

    @Override
    public String changeOrderStatusToDelivered(String id) {
        return changeOrderStatus(id, DeliveryOrderStatus.DELIVERED);
    }

    private String changeOrderStatus(String id, DeliveryOrderStatus status) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        securityUtil.checkUserAssigned(deliveryOrder);
        return changeOrderStatus(deliveryOrder, status);
    }

    private String changeOrderStatus(DeliveryOrder deliveryOrder, DeliveryOrderStatus status) {
        if (deliveryOrder.getStatus().compareTo(status) == 1) {
            throw new DeliveryOrderStatusException(status,
                    String.format("Because order status is %s", deliveryOrder.getStatus().name()));
        }
        if (deliveryOrder.getStatus().compareTo(status) == 0) {
            throw new DeliveryOrderStatusException(status);
        }
        return orderCommand.changeStatus(deliveryOrder.getId(), status);
    }

}
