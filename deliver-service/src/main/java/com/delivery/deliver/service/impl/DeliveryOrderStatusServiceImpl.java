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
    public String changeOrderStatusToPickUp(String id) {
        return changeOrderStatus(id, DeliveryOrderStatus.PENDING, DeliveryOrderStatus.PICKUP,
                "Because order status is not Pending!");
    }

    @Override
    public String changeOrderStatusToDelivery(String id) {
        return changeOrderStatus(id, DeliveryOrderStatus.PICKUP, DeliveryOrderStatus.DELIVERY,
                "Because order status is not Pickup!");
    }

    @Override
    public String changeOrderStatusToDelivered(String id) {
        return changeOrderStatus(id, DeliveryOrderStatus.DELIVERY, DeliveryOrderStatus.DELIVERED,
                "Because order status is not Delivery!");
    }

    private String changeOrderStatus(String id, DeliveryOrderStatus fromStatus, DeliveryOrderStatus toStatus, String message) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        securityUtil.checkUserAssigned(deliveryOrder);
        if (deliveryOrder.getStatus().compareTo(fromStatus) == 0) {
            return orderCommand.changeStatus(deliveryOrder.getId(), toStatus);
        }
        throw new DeliveryOrderStatusException(message);
    }

    @Override
    public String changeOrderStatusToCancel(String id) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        securityUtil.checkUserOwner(deliveryOrder);
        if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.DELIVERED) == 0) {
            throw new DeliveryOrderStatusException("Because order status is  Delivered!");
        }
        return orderCommand.changeStatus(deliveryOrder.getId(), DeliveryOrderStatus.CANCELED);
    }

}
