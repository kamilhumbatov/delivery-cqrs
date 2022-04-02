package com.delivery.aggregates;

import com.delivery.commands.ChangeOrderCoordinateCommand;
import com.delivery.events.DeliverOrderCoordinateChangedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class DeliveryOrderAggregate {

    @AggregateIdentifier
    private Long id;

    private String currentLatitude;

    private String currentLongitude;

    @CommandHandler
    public DeliveryOrderAggregate(ChangeOrderCoordinateCommand command) {
        DeliverOrderCoordinateChangedEvent event = new DeliverOrderCoordinateChangedEvent(
                command.id, command.latitude, command.longitude);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    protected void on(DeliverOrderCoordinateChangedEvent event) {
        this.id = event.id;
        this.currentLatitude = event.latitude;
        this.currentLongitude = event.longitude;
    }
}
