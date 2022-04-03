package com.delivery.deliver.service.handlers;

import com.delivery.deliver.events.*;

public interface DeliveryOrderEventHandler {
    void on(OrderAssignedEvent event);

    void on(CoordinateChangedEvent event);

    void on(DestinationChangedEvent event);

    void on(StatusChangedEvent event);

    void on(DeliverOrderCreatedEvent event);

    void on(DeliverOrderActivatedEvent event);
}
