package com.delivery.deliver.service.impl;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.domain.DeliveryOrderDestination;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.events.DeliverOrderActivatedEvent;
import com.delivery.deliver.events.DeliverOrderCreatedEvent;
import com.delivery.deliver.exception.DeliveryOrderNotFoundException;
import com.delivery.deliver.repository.DeliveryOrderRepository;
import com.delivery.deliver.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    private final DeliveryOrderRepository repository;

    @Override
    public DeliveryOrder findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DeliveryOrderNotFoundException(id));
    }

    @Override
    public List<DeliveryOrder> findAllOrdersByOwner(String username) {
        return repository.findAllByOwner(username);
    }

    @Override
    public List<DeliveryOrder> findAllOrdersByAssignee(String username) {
        return repository.findAllByAssignee(username);
    }

    @Override
    public DeliveryOrder save(DeliveryOrder deliveryOrder) {
        return repository.save(deliveryOrder);
    }

    @EventHandler
    public void on(DeliverOrderCreatedEvent event) {
        DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                .id(event.getOrderId())
                .owner(event.getOwner())
                .status(DeliveryOrderStatus.CREATED)
                .build();

        DeliveryOrderDestination deliveryOrderDestination = DeliveryOrderDestination.builder()
                .latitude(event.getLatitude())
                .longitude(event.getLongitude())
                .order(deliveryOrder)
                .build();
        deliveryOrderDestination.setOrder(deliveryOrder);
        repository.save(deliveryOrder);
    }

    @EventHandler
    public void on(DeliverOrderActivatedEvent event) {
        DeliveryOrder deliveryOrder = findById(event.getId());
        deliveryOrder.setStatus(DeliveryOrderStatus.PENDING);
        repository.save(deliveryOrder);
    }
}
