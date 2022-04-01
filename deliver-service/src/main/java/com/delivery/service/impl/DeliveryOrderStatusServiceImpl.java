package com.delivery.service.impl;

import com.delivery.CurrentUserService;
import com.delivery.dto.DeliveryOrderDto;
import com.delivery.enums.DeliveryOrderStatus;
import com.delivery.exception.DeliveryOrderNotFoundException;
import com.delivery.exception.DeliveryOrderStatusException;
import com.delivery.service.DeliveryOrderStatusCommandService;
import com.delivery.service.mapper.DeliveryOrderMapper;
import com.delivery.user.domain.DeliveryOrder;
import com.delivery.user.repository.DeliveryOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderStatusServiceImpl implements DeliveryOrderStatusCommandService {

    private final DeliveryOrderMapper mapper;
    private final DeliveryOrderRepository repository;
    private final CurrentUserService currentUserService;

    @Override
    public DeliveryOrderDto changeOrderStatusToCancel(long id) {
        DeliveryOrder deliveryOrder = findById(id);
        checkUserAccess(deliveryOrder);
        if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.DELIVERED) == 0) {
            throw new DeliveryOrderStatusException(DeliveryOrderStatus.CANCELED, "Because order was delivered!");
        }
        return changeOrderStatus(deliveryOrder, DeliveryOrderStatus.CANCELED);
    }

    @Override
    public DeliveryOrderDto changeOrderStatusToPickUp(long id) {
        return changeOrderStatus(id, DeliveryOrderStatus.PICKUP);
    }

    @Override
    public DeliveryOrderDto changeOrderStatusToDelivery(long id) {
        return changeOrderStatus(id, DeliveryOrderStatus.DELIVERY);
    }

    @Override
    public DeliveryOrderDto changeOrderStatusToDelivered(long id) {
        return changeOrderStatus(id, DeliveryOrderStatus.DELIVERED);
    }

    private void checkUserAccess(DeliveryOrder deliveryOrder) {
        if (!deliveryOrder.getAssignee().equals(currentUserService.getCurrentUser())) {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    private DeliveryOrderDto changeOrderStatus(long id, DeliveryOrderStatus status) {
        DeliveryOrder deliveryOrder = findById(id);
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
        return mapper.toDto(repository.save(deliveryOrder));
    }

    private DeliveryOrder findById(long id) {
        return repository.findById(id).orElseThrow(() -> new DeliveryOrderNotFoundException(id));
    }
}
