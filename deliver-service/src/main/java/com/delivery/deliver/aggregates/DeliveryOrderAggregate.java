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

    @CommandHandler
    public DeliveryOrderAggregate(CreateOrderCommand command) {
        DeliverOrderCreatedEvent event = new DeliverOrderCreatedEvent(
                command.id, command.owner, command.latitude, command.longitude);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    protected void on(DeliverOrderCreatedEvent event) {
        this.id = event.id;
        this.owner = owner;
        this.latitude = event.latitude;
        this.longitude = event.longitude;
    }

    @CommandHandler
    public DeliveryOrderAggregate(ChangeOrderCoordinateCommand command) {
        DeliverOrderCoordinateChangedEvent event = new DeliverOrderCoordinateChangedEvent(
                command.id, command.latitude, command.longitude);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    protected void on(DeliverOrderCoordinateChangedEvent event) {
        this.currentLatitude = event.latitude;
        this.currentLongitude = event.longitude;
    }
}
