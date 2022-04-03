package com.delivery.deliver.service.events;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.events.StatusChangedEvent;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryOrderStatusEventHandler {

    private final DeliveryOrderService deliveryOrderService;
    private final KafkaProducerService kafkaProducerService;

    @EventHandler
    public void on(StatusChangedEvent event) {
        DeliveryOrder order = deliveryOrderService.findById(event.getOrderId());
        order.setStatus(event.getStatus());
        deliveryOrderService.save(order);
        kafkaProducerService.sendOrderStatus(order);
    }
}
