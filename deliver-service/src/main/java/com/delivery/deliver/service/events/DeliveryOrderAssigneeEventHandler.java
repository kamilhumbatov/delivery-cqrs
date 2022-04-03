package com.delivery.deliver.service.events;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.events.OrderAssignedEvent;
import com.delivery.deliver.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryOrderAssigneeEventHandler {

    private final DeliveryOrderService deliveryOrderService;

    @EventHandler
    public void on(OrderAssignedEvent event) {
        DeliveryOrder order = deliveryOrderService.findById(event.getOrderId());
        order.setAssignee(event.getAssignee());
        deliveryOrderService.save(order);
    }
}
