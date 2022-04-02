package com.delivery.aggregates;

import com.delivery.commands.ChangeOrderCoordinateCommand;
import com.delivery.commands.ChangeStatusDeliveryOrderCommand;
import com.delivery.dto.DeliveryOrderDestinationDto;
import com.delivery.enums.DeliveryOrderStatus;
import com.delivery.events.DeliverOrderCoordinateChangeEvent;
import com.delivery.events.DeliverOrderStatusChangeEvent;
import com.delivery.service.DeliveryOrderCoordinateService;
import com.delivery.service.DeliveryOrderService;
import com.delivery.user.domain.DeliveryOrder;
import com.delivery.user.domain.DeliveryOrderCoordinate;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

@Aggregate
public class DeliveryOrderAggregate {

    @AggregateIdentifier
    private Long id;

    private DeliveryOrderStatus status;

    @Autowired
    private DeliveryOrderService orderService;

    @Autowired
    private DeliveryOrderCoordinateService orderCoordinateService;

    @CommandHandler
    public DeliveryOrderAggregate(ChangeStatusDeliveryOrderCommand changeStatusDeliveryOrderCommand) {
        DeliveryOrder deliveryOrder = changeStatusDeliveryOrderCommand.getOrder();
        deliveryOrder.setStatus(changeStatusDeliveryOrderCommand.getStatus());
        AggregateLifecycle.apply(new DeliverOrderStatusChangeEvent(deliveryOrder));
    }

    @EventSourcingHandler
    protected void on(DeliverOrderStatusChangeEvent deliverOrderStatusChangeEvent) {
        DeliveryOrder deliveryOrder = deliverOrderStatusChangeEvent.getOrder();
        this.id = deliveryOrder.getId();
        this.status = deliveryOrder.getStatus();
        orderService.save(deliveryOrder);
    }

    @CommandHandler
    public DeliveryOrderAggregate(ChangeOrderCoordinateCommand changeOrderCoordinateCommand) {
        DeliveryOrderDestinationDto coordinate = changeOrderCoordinateCommand.getCoordinate()
        DeliveryOrderCoordinate deliveryOrderCoordinate = DeliveryOrderCoordinate.builder()
                .latitude(coordinate.getLatitude())
                .longitude(coordinate.getLongitude())
                .order(changeOrderCoordinateCommand.getOrder())
                .build();
        AggregateLifecycle.apply(new DeliverOrderStatusChangeEvent(deliveryOrderCoordinate));
    }

    @EventSourcingHandler
    protected void on(DeliverOrderCoordinateChangeEvent deliverOrderCoordinateChangeEvent) {
        DeliveryOrderCoordinate deliveryOrderCoordinate = deliverOrderCoordinateChangeEvent.getCoordinate();
        //this.id = deliveryOrder.getId();
        //this.status = deliveryOrder.getStatus();
        //orderCoordinateService.save(deliveryOrderCoordinate);
    }
}
