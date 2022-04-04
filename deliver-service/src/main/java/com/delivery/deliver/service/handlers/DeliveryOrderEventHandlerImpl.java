package com.delivery.deliver.service.handlers;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.domain.DeliveryOrderCoordinate;
import com.delivery.deliver.domain.DeliveryOrderDestination;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.events.CoordinateChangedEvent;
import com.delivery.deliver.events.DeliverOrderActivatedEvent;
import com.delivery.deliver.events.DeliverOrderCreatedEvent;
import com.delivery.deliver.events.DestinationChangedEvent;
import com.delivery.deliver.events.OrderAssignedEvent;
import com.delivery.deliver.events.StatusChangedEvent;
import com.delivery.deliver.repository.DeliveryOrderCoordinateRepository;
import com.delivery.deliver.service.DeliveryOrderDestinationService;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("order-group")
@RequiredArgsConstructor
public class DeliveryOrderEventHandlerImpl implements DeliveryOrderEventHandler {

    private final DeliveryOrderService deliveryOrderService;
    private final DeliveryOrderDestinationService destinationService;
    private final DeliveryOrderCoordinateRepository repository;
    private final KafkaProducerService kafkaProducerService;

    @Override
    @EventHandler
    public void on(OrderAssignedEvent event) {
        DeliveryOrder order = deliveryOrderService.findById(event.getOrderId());
        order.setAssignee(event.getAssignee());
        deliveryOrderService.save(order);
    }

    @Override
    @EventHandler
    public void on(CoordinateChangedEvent event) {
        DeliveryOrder order = deliveryOrderService.findById(event.getOrderId());
        DeliveryOrderCoordinate orderCoordinate = DeliveryOrderCoordinate.builder()
                .latitude(event.getLatitude())
                .longitude(event.getLongitude())
                .order(order)
                .build();
        repository.save(orderCoordinate);
    }

    @Override
    @EventHandler
    public void on(DestinationChangedEvent event) {
        DeliveryOrder order = deliveryOrderService.findById(event.getOrderId());
        DeliveryOrderDestination deliveryOrderDestination = order.getDestination();
        deliveryOrderDestination.setLatitude(event.getLatitude());
        deliveryOrderDestination.setLongitude(event.getLongitude());
        destinationService.updateDestination(deliveryOrderDestination);
    }

    @Override
    @EventHandler
    public void on(StatusChangedEvent event) {
        DeliveryOrder order = deliveryOrderService.findById(event.getOrderId());
        order.setStatus(event.getStatus());
        deliveryOrderService.save(order);
        kafkaProducerService.sendOrderStatus(order);
    }

    @Override
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
        deliveryOrder.setDestination(deliveryOrderDestination);
        deliveryOrderService.save(deliveryOrder);
    }

    @Override
    @EventHandler
    public void on(DeliverOrderActivatedEvent event) {
        DeliveryOrder deliveryOrder = deliveryOrderService.findById(event.getId());
        deliveryOrder.setStatus(DeliveryOrderStatus.PENDING);
        deliveryOrderService.save(deliveryOrder);
    }
}
