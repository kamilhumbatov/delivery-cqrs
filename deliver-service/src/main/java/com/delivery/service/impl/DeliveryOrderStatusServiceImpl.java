package com.delivery.service.impl;

import com.delivery.CurrentUserService;
import com.delivery.commands.ChangeStatusDeliveryOrderCommand;
import com.delivery.enums.DeliveryOrderStatus;
import com.delivery.exception.DeliveryOrderStatusException;
import com.delivery.service.DeliveryOrderService;
import com.delivery.service.DeliveryOrderStatusService;
import com.delivery.user.domain.DeliveryOrder;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class DeliveryOrderStatusServiceImpl implements DeliveryOrderStatusService {

    private final DeliveryOrderService orderService;
    private final CurrentUserService currentUserService;
    private final CommandGateway commandGateway;

    @Override
    public CompletableFuture<String> changeOrderStatusToCancel(long id) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        checkUserAccess(deliveryOrder);
        if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.DELIVERED) == 0) {
            throw new DeliveryOrderStatusException(DeliveryOrderStatus.CANCELED, "Because order was delivered!");
        }
        return changeOrderStatus(deliveryOrder, DeliveryOrderStatus.CANCELED);
    }

    @Override
    public CompletableFuture<String> changeOrderStatusToPickUp(long id) {
        return changeOrderStatus(id, DeliveryOrderStatus.PICKUP);
    }

    @Override
    public CompletableFuture<String> changeOrderStatusToDelivery(long id) {
        return changeOrderStatus(id, DeliveryOrderStatus.DELIVERY);
    }

    @Override
    public CompletableFuture<String> changeOrderStatusToDelivered(long id) {
        return changeOrderStatus(id, DeliveryOrderStatus.DELIVERED);
    }

    private void checkUserAccess(DeliveryOrder deliveryOrder) {
        if (!deliveryOrder.getAssignee().equals(currentUserService.getCurrentUser())) {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    private CompletableFuture<String> changeOrderStatus(long id, DeliveryOrderStatus status) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        checkUserAccess(deliveryOrder);
        return changeOrderStatus(deliveryOrder, status);
    }

    private CompletableFuture<String> changeOrderStatus(DeliveryOrder deliveryOrder, DeliveryOrderStatus status) {
        if (deliveryOrder.getStatus().compareTo(status) == 1) {
            throw new DeliveryOrderStatusException(status,
                    String.format("Because order status is %s", deliveryOrder.getStatus().name()));
        }
        if (deliveryOrder.getStatus().compareTo(status) == 0) {
            throw new DeliveryOrderStatusException(status);
        }
        deliveryOrder.setStatus(status);
        return commandGateway.send(new ChangeStatusDeliveryOrderCommand(deliveryOrder, status));
    }

}
