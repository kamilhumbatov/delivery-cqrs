package com.delivery.deliver.service.events;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.domain.DeliveryOrderDestination;
import com.delivery.deliver.events.CoordinateChangedEvent;
import com.delivery.deliver.service.DeliveryOrderDestinationService;
import com.delivery.deliver.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryOrderDestinationEventHandler {

    private final DeliveryOrderService deliveryOrderService;
    private final DeliveryOrderDestinationService destinationService;

    @EventHandler
    public void on(CoordinateChangedEvent event) {
        DeliveryOrder order = deliveryOrderService.findById(event.getOrderId());
        DeliveryOrderDestination deliveryOrderDestination = order.getDestination();
        deliveryOrderDestination.setLatitude(event.getLatitude());
        deliveryOrderDestination.setLongitude(event.getLongitude());
        destinationService.updateDestination(deliveryOrderDestination);
    }
}
