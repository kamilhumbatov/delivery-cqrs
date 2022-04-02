package com.delivery.deliver.service.impl;

import com.delivery.CurrentUserService;
import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.dto.DeliveryOrderDto;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.exception.DeliveryOrderStatusException;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.DeliveryOrderStatusService;
import com.delivery.deliver.service.mapper.DeliveryOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderStatusServiceImpl implements DeliveryOrderStatusService {

    private final DeliveryOrderMapper mapper;
    private final DeliveryOrderService orderService;
    private final CurrentUserService currentUserService;

    @Override
    public DeliveryOrderDto changeOrderStatusToCancel(String id) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        checkUserAccess(deliveryOrder);
        if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.DELIVERED) == 0) {
            throw new DeliveryOrderStatusException(DeliveryOrderStatus.CANCELED, "Because order was delivered!");
        }
        return changeOrderStatus(deliveryOrder, DeliveryOrderStatus.CANCELED);
    }

    @Override
    public DeliveryOrderDto changeOrderStatusToPickUp(String id) {
        return changeOrderStatus(id, DeliveryOrderStatus.PICKUP);
    }

    @Override
    public DeliveryOrderDto changeOrderStatusToDelivery(String id) {
        return changeOrderStatus(id, DeliveryOrderStatus.DELIVERY);
    }

    @Override
    public DeliveryOrderDto changeOrderStatusToDelivered(String id) {
        return changeOrderStatus(id, DeliveryOrderStatus.DELIVERED);
    }

    private void checkUserAccess(DeliveryOrder deliveryOrder) {
        if (!deliveryOrder.getAssignee().equals(currentUserService.getCurrentUser())) {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    private DeliveryOrderDto changeOrderStatus(String id, DeliveryOrderStatus status) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        checkUserAccess(deliveryOrder);
        return changeOrderStatus(deliveryOrder, status);
    }

    private DeliveryOrderDto changeOrderStatus(DeliveryOrder deliveryOrder, DeliveryOrderStatus status) {
        if (deliveryOrder.getStatus().compareTo(status) == 1) {
            throw new DeliveryOrderStatusException(status,
                    String.format("Because order status is %s", deliveryOrder.getStatus().name()));
        }
        if (deliveryOrder.getStatus().compareTo(status) == 0) {
            throw new DeliveryOrderStatusException(status);
        }
        deliveryOrder.setStatus(status);
        return mapper.toDto(orderService.save(deliveryOrder));
    }

}
