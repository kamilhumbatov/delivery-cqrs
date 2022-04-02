package com.delivery.deliver.aggregates;

import com.delivery.deliver.commands.ChangeOrderCoordinateCommand;
import com.delivery.deliver.commands.CreateOrderCommand;
import com.delivery.deliver.events.DeliverOrderCoordinateChangedEvent;
import com.delivery.deliver.events.DeliverOrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class DeliveryOrderAggregate {

    @AggregateIdentifier
    private String id;

    private String owner;

    private String latitude;

    private String longitude;

    private String currentLatitude;

    private String currentLongitude;

    public DeliveryOrderAggregate() {

    }

    @CommandHandler
    public DeliveryOrderAggregate(CreateOrderCommand command) {
        DeliverOrderCreatedEvent event =
                DeliverOrderCreatedEvent.builder()
                        .orderId(command.getOrderId())
                        .latitude(command.getLatitude())
                        .longitude(command.getLongitude())
                        .owner(command.getOwner())
                        .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    protected void on(DeliverOrderCreatedEvent event) {
        this.id = event.getOrderId();
        this.owner = event.getOwner();
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
    }

    @CommandHandler
    public DeliveryOrderAggregate(ChangeOrderCoordinateCommand command) {
        DeliverOrderCoordinateChangedEvent event =
                DeliverOrderCoordinateChangedEvent.builder()
                        .orderId(command.getOrderId())
                        .latitude(command.getLatitude())
                        .longitude(command.getLongitude())
                        .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    protected void on(DeliverOrderCoordinateChangedEvent event) {
        this.currentLatitude = event.getLatitude();
        this.currentLongitude = event.getLongitude();
    }
}
