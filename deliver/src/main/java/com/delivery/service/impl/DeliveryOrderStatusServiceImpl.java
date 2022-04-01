package com.delivery.service.impl;

import com.delivery.dto.DeliveryOrderDto;
import com.delivery.enums.DeliveryOrderStatus;
import com.delivery.service.DeliveryOrderStatusService;
import com.delivery.service.mapper.DeliveryOrderMapper;
import com.delivery.user.domain.DeliveryOrder;
import com.delivery.user.repository.DeliveryOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderStatusServiceImpl implements DeliveryOrderStatusService {

    private final DeliveryOrderRepository repository;
    private final DeliveryOrderMapper mapper;

    @Override
    public DeliveryOrderDto changeOrderStatusToCancel(Long id) {
        DeliveryOrder deliveryOrder = findById(id);
        if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.DELIVERED) == 0) {
            throw new IllegalArgumentException("Can not cancelled");
        }
        if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.CANCELED) == 0) {
            throw new IllegalArgumentException("Order cancelled");
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.CANCELED);
        return mapper.toDto(repository.save(deliveryOrder));
    }

    @Override
    public DeliveryOrderDto changeOrderStatusToPickUp(Long id) {
        return changeOrderStatus(id, DeliveryOrderStatus.PICKUP);
    }

    @Override
    public DeliveryOrderDto changeOrderStatusToDelivery(Long id) {
        return changeOrderStatus(id, DeliveryOrderStatus.DELIVERY);
    }

    @Override
    public DeliveryOrderDto changeOrderStatusToDelivered(Long id) {
        return changeOrderStatus(id, DeliveryOrderStatus.DELIVERED);
    }

    private DeliveryOrderDto changeOrderStatus(Long id, DeliveryOrderStatus status) {
        DeliveryOrder deliveryOrder = findById(id);
        deliveryOrder.setStatus(status);
        return mapper.toDto(repository.save(deliveryOrder));
    }

    private DeliveryOrder findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                String.format("Invalid DeliveryOrder id %s", id)));
    }
}
