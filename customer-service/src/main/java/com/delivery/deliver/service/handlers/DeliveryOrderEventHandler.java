package com.delivery.deliver.service.handlers;

import com.delivery.deliver.events.CoordinateChangedEvent;
import com.delivery.deliver.events.DeliverOrderActivatedEvent;
import com.delivery.deliver.events.DeliverOrderCreatedEvent;
import com.delivery.deliver.events.DestinationChangedEvent;
import com.delivery.deliver.events.OrderAssignedEvent;
import com.delivery.deliver.events.StatusChangedEvent;

public interface DeliveryOrderEventHandler {
    void on(OrderAssignedEvent event);

    void on(CoordinateChangedEvent event);

    void on(DestinationChangedEvent event);

    void on(StatusChangedEvent event);

    void on(DeliverOrderCreatedEvent event);

    void on(DeliverOrderActivatedEvent event);
}
