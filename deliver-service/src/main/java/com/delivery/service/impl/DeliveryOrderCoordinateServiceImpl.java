package com.delivery.service.impl;

import com.delivery.domain.DeliveryOrder;
import com.delivery.domain.DeliveryOrderCoordinate;
import com.delivery.events.DeliverOrderCoordinateChangedEvent;
import com.delivery.repository.DeliveryOrderCoordinateRepository;
import com.delivery.service.DeliveryOrderCoordinateService;
import com.delivery.service.DeliveryOrderDestinationService;
import com.delivery.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryOrderCoordinateServiceImpl implements DeliveryOrderCoordinateService {

    private final DeliveryOrderService deliveryOrderService;
    private final DeliveryOrderDestinationService destinationService;
    private final DeliveryOrderCoordinateRepository repository;

    @EventHandler
    public void on(DeliverOrderCoordinateChangedEvent event) {
        DeliveryOrder order = deliveryOrderService.findById(event.id);
        DeliveryOrderCoordinate orderCoordinate = DeliveryOrderCoordinate.builder()
                .latitude(event.latitude)
                .longitude(event.longitude)
                .order(order)
                .build();
        repository.save(orderCoordinate);
        destinationService.updateLastCoordinate(orderCoordinate);
    }
}
