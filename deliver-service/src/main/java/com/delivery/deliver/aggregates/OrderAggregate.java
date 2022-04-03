package com.delivery.deliver.aggregates;

import com.delivery.deliver.commands.*;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.events.*;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

@Data
@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String id;

    private String owner;

    private String assignee;

    private DeliveryOrderDestinationDto destination;

    private DeliveryOrderDestinationDto currentLocation;

    private DeliveryOrderStatus statusDelivery;

    private List<DeliveryOrderDestinationDto> coordinates;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        System.out.println("CreateOrderCommand");
        var event = DeliverOrderCreatedEvent.builder()
                .orderId(command.getOrderId())
                .latitude(command.getLatitude())
                .longitude(command.getLongitude())
                .owner(command.getOwner())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(DeliverOrderCreatedEvent event) {
        System.out.println("DeliverOrderCreatedEvent");
        this.id = event.getOrderId();
        this.owner = event.getOwner();
        this.destination = DeliveryOrderDestinationDto.builder()
                .latitude(event.getLatitude())
                .longitude(event.getLongitude())
                .build();
        this.statusDelivery = DeliveryOrderStatus.CREATED;
        this.coordinates = new ArrayList<>();
        AggregateLifecycle.apply(new DeliverOrderActivatedEvent(this.id, DeliveryOrderStatus.PENDING));
    }

    @EventSourcingHandler
    protected void on(DeliverOrderActivatedEvent accountActivatedEvent) {
        System.out.println("AccountActivatedEvent");
        this.statusDelivery = accountActivatedEvent.getStatus();
    }

    @CommandHandler
    public void handle(ChangeAssigneeCommand command) {
        System.out.println("AssigneeOrderCommand");
        var event = OrderAssignedEvent.builder()
                .orderId(command.getId())
                .assignee(command.getAssignee())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    protected void on(OrderAssignedEvent orderAssignedEvent) {
        System.out.println("OrderAssignedEvent");
        this.assignee = orderAssignedEvent.getAssignee();
    }

    @CommandHandler
    protected void handle(ChangeCoordinateCommand command) {
        System.out.println("ChangeCoordinateCommand");
        var event = CoordinateChangedEvent.builder()
                .orderId(command.getId())
                .latitude(command.getLatitude())
                .longitude(command.getLongitude())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CoordinateChangedEvent event) {
        DeliveryOrderDestinationDto currentLocation = DeliveryOrderDestinationDto.builder()
                .latitude(event.getLatitude())
                .longitude(event.getLongitude())
                .build();
        this.currentLocation = currentLocation;
        this.coordinates.add(currentLocation);
        System.out.println("DeliverOrderCoordinateChangedEvent:" + this.coordinates.size());
    }

    @CommandHandler
    protected void handle(ChangeDestinationCommand command) {
        var event = CoordinateChangedEvent.builder()
                .orderId(command.getId())
                .latitude(command.getLatitude())
                .longitude(command.getLongitude())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(DestinationChangedEvent event) {
        this.destination = DeliveryOrderDestinationDto.builder()
                .latitude(event.getLatitude())
                .longitude(event.getLongitude())
                .build();
        System.out.println("DestinationChangedEvent");
    }

    @CommandHandler
    protected void handle(ChangeStatusCommand command) {
        System.out.println("ChangeStatusCommand");
        var event = StatusChangedEvent.builder()
                .orderId(command.getId())
                .status(command.getStatus())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(StatusChangedEvent event) {
        this.statusDelivery = event.getStatus();
        System.out.println("StatusChangedEvent:" + this.statusDelivery.name());
    }
}
