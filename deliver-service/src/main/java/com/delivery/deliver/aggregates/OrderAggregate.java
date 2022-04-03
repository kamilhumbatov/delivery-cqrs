package com.delivery.deliver.aggregates;

import com.delivery.deliver.commands.ChangeCoordinateCommand;
import com.delivery.deliver.commands.CreateOrderCommand;
import com.delivery.deliver.commands.CreditMoneyCommand;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.events.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String id;

    private String status;

    private String owner;

    private String latitude;

    private String longitude;

    private String currentLatitude;

    private String currentLongitude;

    private DeliveryOrderStatus statusDelivery;

    private List<String> coordinates;

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
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
        this.statusDelivery = DeliveryOrderStatus.CREATED;
        this.coordinates = new ArrayList<>();
        AggregateLifecycle.apply(new DeliverOrderActivatedEvent(this.id, DeliveryOrderStatus.ACTIVATED));
    }

    @EventSourcingHandler
    protected void on(DeliverOrderActivatedEvent accountActivatedEvent) {
        System.out.println("AccountActivatedEvent");
        this.statusDelivery = accountActivatedEvent.getStatus();
    }

    @CommandHandler
    public OrderAggregate(ChangeCoordinateCommand command) {
        System.out.println("ChangeCoordinateCommand");
        var event = DeliverOrderCoordinateChangedEvent.builder()
                .orderId(command.getOrderId())
                .latitude(command.getLatitude())
                .longitude(command.getLongitude())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(DeliverOrderCoordinateChangedEvent event) {
        System.out.println("DeliverOrderCoordinateChangedEvent");
        this.currentLatitude = event.getLatitude();
        this.currentLongitude = event.getLongitude();
        //this.coordinates.add(event.getLatitude());
    }

    @EventSourcingHandler
    protected void on(AccountActivatedEvent accountActivatedEvent) {
        System.out.println("AccountActivatedEvent");
        this.status = String.valueOf(accountActivatedEvent.getStatus());
    }

    @CommandHandler
    protected void on(CreditMoneyCommand command) {
        System.out.println("CreditMoneyCommand");
//        AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.getId(), creditMoneyCommand.getCreditAmount(),
//                creditMoneyCommand.getCurrency()));
        System.out.println("ChangeCoordinateCommand");
        var event = DeliverOrderCoordinateChangedEvent.builder()
                .orderId(command.getId())
                .latitude(command.getLatitude())
                .longitude(command.getLongitude())
                .build();
        AggregateLifecycle.apply(event);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(String currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public String getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(String currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public DeliveryOrderStatus getStatusDelivery() {
        return statusDelivery;
    }

    public void setStatusDelivery(DeliveryOrderStatus statusDelivery) {
        this.statusDelivery = statusDelivery;
    }

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }
}
