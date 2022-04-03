package com.delivery.deliver.service.events;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.domain.DeliveryOrderCoordinate;
import com.delivery.deliver.events.CoordinateChangedEvent;
import com.delivery.deliver.repository.DeliveryOrderCoordinateRepository;
import com.delivery.deliver.service.DeliveryOrderDestinationService;
import com.delivery.deliver.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryOrderCoordinateEventHandler {

    private final DeliveryOrderService deliveryOrderService;
    private final DeliveryOrderDestinationService destinationService;
    private final DeliveryOrderCoordinateRepository repository;

    @EventHandler
    public void on(CoordinateChangedEvent event) {
        DeliveryOrder order = deliveryOrderService.findById(event.getOrderId());
        DeliveryOrderCoordinate orderCoordinate = DeliveryOrderCoordinate.builder()
                .latitude(event.getLatitude())
                .longitude(event.getLongitude())
                .order(order)
                .build();
        repository.save(orderCoordinate);
        destinationService.updateLastCoordinate(orderCoordinate);
    }
}
